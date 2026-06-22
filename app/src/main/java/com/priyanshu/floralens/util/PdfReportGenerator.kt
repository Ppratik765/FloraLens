package com.priyanshu.floralens.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import com.priyanshu.floralens.data.PlantProfile
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PdfReportGenerator {

    private const val PAGE_WIDTH = 595   // A4 at 72 DPI
    private const val PAGE_HEIGHT = 842
    private const val MARGIN = 40f
    private const val CONTENT_WIDTH = PAGE_WIDTH - 2 * MARGIN.toInt()

    fun generateReport(context: Context, profile: PlantProfile): Uri? {
        return try {
            val document = PdfDocument()
            var pageNumber = 1
            var currentY = MARGIN

            // Helper to create a new page
            fun newPage(): PdfDocument.Page {
                val pageInfo = PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageNumber++).create()
                return document.startPage(pageInfo)
            }

            var page = newPage()
            var canvas: Canvas = page.canvas

            // ── Header block ──
            val headerPaint = Paint().apply {
                color = 0xFF2AFF73.toInt()
                style = Paint.Style.FILL
            }
            canvas.drawRect(0f, 0f, PAGE_WIDTH.toFloat(), 100f, headerPaint)

            val titlePaint = TextPaint().apply {
                color = 0xFF0D1F12.toInt()
                textSize = 28f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }
            canvas.drawText("FloraLens Report", MARGIN, 45f, titlePaint)

            val subtitlePaint = TextPaint().apply {
                color = 0xFF0D1F12.toInt()
                textSize = 18f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                isAntiAlias = true
            }
            canvas.drawText(profile.customName, MARGIN, 75f, subtitlePaint)

            // Date
            val datePaint = TextPaint().apply {
                color = 0xFF0D1F12.toInt()
                textSize = 11f
                isAntiAlias = true
            }
            val dateStr = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
            val dateWidth = datePaint.measureText(dateStr)
            canvas.drawText(dateStr, PAGE_WIDTH - MARGIN - dateWidth, 75f, datePaint)

            currentY = 120f

            // ── Scan entries ──
            val sectionTitlePaint = TextPaint().apply {
                color = 0xFF00C853.toInt()
                textSize = 16f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }
            val bodyPaint = TextPaint().apply {
                color = 0xFF333333.toInt()
                textSize = 12f
                isAntiAlias = true
            }
            val labelPaint = TextPaint().apply {
                color = 0xFF00C853.toInt()
                textSize = 12f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }
            val dateFormat = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault())

            for ((index, scan) in profile.scans.sortedByDescending { it.timestamp }.withIndex()) {

                // Estimate block height: image(150) + text(~120) + spacing(40)
                val blockHeight = 320f
                if (currentY + blockHeight > PAGE_HEIGHT - MARGIN) {
                    document.finishPage(page)
                    page = newPage()
                    canvas = page.canvas
                    currentY = MARGIN
                }

                // Divider
                if (index > 0) {
                    val dividerPaint = Paint().apply {
                        color = 0xFFCCCCCC.toInt()
                        strokeWidth = 1f
                    }
                    canvas.drawLine(MARGIN, currentY, PAGE_WIDTH - MARGIN, currentY, dividerPaint)
                    currentY += 16f
                }

                // Scan number + date
                canvas.drawText(
                    "Scan #${profile.scans.size - index} — ${dateFormat.format(Date(scan.timestamp))}",
                    MARGIN, currentY + 14f, sectionTitlePaint
                )
                currentY += 28f

                // Image thumbnail
                if (scan.imagePath.isNotEmpty()) {
                    val imageFile = File(scan.imagePath)
                    if (imageFile.exists()) {
                        val bitmap = BitmapFactory.decodeFile(scan.imagePath)
                        if (bitmap != null) {
                            val imgWidth = 150
                            val imgHeight = (imgWidth * bitmap.height.toFloat() / bitmap.width).toInt()
                                .coerceAtMost(150)
                            val srcRect = Rect(0, 0, bitmap.width, bitmap.height)
                            val destRect = Rect(
                                MARGIN.toInt(),
                                currentY.toInt(),
                                MARGIN.toInt() + imgWidth,
                                currentY.toInt() + imgHeight
                            )
                            canvas.drawBitmap(bitmap, srcRect, destRect, null)
                            bitmap.recycle()

                            // Text beside image
                            val textX = MARGIN + imgWidth + 16f
                            val textWidth = (CONTENT_WIDTH - imgWidth - 16).coerceAtLeast(100)
                            var textY = currentY

                            canvas.drawText("Disease:", textX, textY + 14f, labelPaint)
                            textY += 18f
                            val diseaseLayout = createStaticLayout(scan.diseaseName, bodyPaint, textWidth)
                            canvas.save()
                            canvas.translate(textX, textY)
                            diseaseLayout.draw(canvas)
                            canvas.restore()
                            textY += diseaseLayout.height + 8f

                            canvas.drawText("Cause:", textX, textY + 14f, labelPaint)
                            textY += 18f
                            val causeLayout = createStaticLayout(scan.cause, bodyPaint, textWidth)
                            canvas.save()
                            canvas.translate(textX, textY)
                            causeLayout.draw(canvas)
                            canvas.restore()
                            textY += causeLayout.height + 8f

                            canvas.drawText("Treatment:", textX, textY + 14f, labelPaint)
                            textY += 18f
                            val treatLayout = createStaticLayout(scan.treatment, bodyPaint, textWidth)
                            canvas.save()
                            canvas.translate(textX, textY)
                            treatLayout.draw(canvas)
                            canvas.restore()
                            textY += treatLayout.height

                            currentY += maxOf(imgHeight.toFloat(), textY - currentY) + 20f
                        } else {
                            currentY = drawTextOnlyScan(canvas, scan, currentY, bodyPaint, labelPaint, dateFormat)
                        }
                    } else {
                        currentY = drawTextOnlyScan(canvas, scan, currentY, bodyPaint, labelPaint, dateFormat)
                    }
                } else {
                    currentY = drawTextOnlyScan(canvas, scan, currentY, bodyPaint, labelPaint, dateFormat)
                }
            }

            document.finishPage(page)

            // Save to Downloads
            val fileName = "FloraLens_${profile.customName.replace(" ", "_")}_${System.currentTimeMillis()}.pdf"
            val savedUri = saveToDownloads(context, document, fileName)
            document.close()
            savedUri
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun drawTextOnlyScan(
        canvas: Canvas,
        scan: com.priyanshu.floralens.data.ScanResult,
        startY: Float,
        bodyPaint: TextPaint,
        labelPaint: TextPaint,
        dateFormat: SimpleDateFormat
    ): Float {
        var y = startY

        canvas.drawText("Disease:", MARGIN, y + 14f, labelPaint)
        y += 18f
        val diseaseLayout = createStaticLayout(scan.diseaseName, bodyPaint, CONTENT_WIDTH)
        canvas.save()
        canvas.translate(MARGIN, y)
        diseaseLayout.draw(canvas)
        canvas.restore()
        y += diseaseLayout.height + 8f

        canvas.drawText("Cause:", MARGIN, y + 14f, labelPaint)
        y += 18f
        val causeLayout = createStaticLayout(scan.cause, bodyPaint, CONTENT_WIDTH)
        canvas.save()
        canvas.translate(MARGIN, y)
        causeLayout.draw(canvas)
        canvas.restore()
        y += causeLayout.height + 8f

        canvas.drawText("Treatment:", MARGIN, y + 14f, labelPaint)
        y += 18f
        val treatLayout = createStaticLayout(scan.treatment, bodyPaint, CONTENT_WIDTH)
        canvas.save()
        canvas.translate(MARGIN, y)
        treatLayout.draw(canvas)
        canvas.restore()
        y += treatLayout.height + 20f

        return y
    }

    @Suppress("DEPRECATION")
    private fun createStaticLayout(text: String, paint: TextPaint, width: Int): StaticLayout {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(text, 0, text.length, paint, width)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(2f, 1f)
                .build()
        } else {
            StaticLayout(text, paint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 2f, false)
        }
    }

    private fun saveToDownloads(context: Context, document: PdfDocument, fileName: String): Uri? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                    put(MediaStore.Downloads.MIME_TYPE, "application/pdf")
                    put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val uri = context.contentResolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                    contentValues
                ) ?: return null

                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    document.writeTo(outputStream)
                }
                uri
            } else {
                @Suppress("DEPRECATION")
                val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                if (!downloadsDir.exists()) downloadsDir.mkdirs()
                
                val file = File(downloadsDir, fileName)
                FileOutputStream(file).use { outputStream ->
                    document.writeTo(outputStream)
                }
                
                androidx.core.content.FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    file
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

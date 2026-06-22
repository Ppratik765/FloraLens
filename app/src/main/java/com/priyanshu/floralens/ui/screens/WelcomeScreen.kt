package com.priyanshu.floralens.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.priyanshu.floralens.ui.components.BotanicalBackgroundDecorations
import com.priyanshu.floralens.ui.theme.*

@Composable
fun WelcomeScreen() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BotanicalBackgroundDecorations()



        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
                .padding(top = 72.dp, bottom = 120.dp), // margin for bottom nav
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp, RoundedCornerShape(28.dp))
                    .clip(RoundedCornerShape(28.dp))
                    .background(PureWhite)
                    .border(2.dp, LeafGreen, RoundedCornerShape(28.dp))
                    .padding(28.dp),
                contentAlignment = Alignment.Center
            ) {
                // Flower SVG inside header card
                Icon(
                    imageVector = Icons.Filled.LocalFlorist,
                    contentDescription = null,
                    tint = BotanicalGreen.copy(alpha = 0.2f),
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.Center)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Welcome to",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextLight,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "FloraLens",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = LeafGreen,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Your digital botanical garden assistant. Scan any plant to instantly diagnose diseases and get organic treatments.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextDark,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // --- SECTION 1: HOW THE APP WORKS ---
            SectionHeader(
                title = "How the App Works",
                icon = Icons.Filled.Settings
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                icon = Icons.Filled.CameraAlt,
                title = "1. Point & Snap",
                description = "Navigate to the Scan screen, align a diseased or healthy leaf inside the camera preview frame, and tap the Diagnose button."
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                icon = Icons.Filled.Memory,
                title = "2. AI Diagnostics",
                description = "On-device AI instantly scans the leaf structure, running the image pixels through a high-performance quantized neural network."
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                icon = Icons.Filled.LocalFlorist,
                title = "3. Organic Treatment",
                description = "If a disease is detected, the app displays its causes and complete organic treatments."
            )

            Spacer(modifier = Modifier.height(28.dp))

            // --- SECTION 2: AI MODEL TRAINING & TECH ---
            SectionHeader(
                title = "How We Trained the Model",
                icon = Icons.Filled.Info
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(PureWhite)
                    .border(1.5.dp, PastelGreenCard, RoundedCornerShape(24.dp))
                    .padding(20.dp)
            ) {
                Column {
                    TechRow(
                        icon = Icons.Filled.Eco,
                        title = "Dataset & Diversity",
                        description = "Trained using the comprehensive PlantVillage dataset, containing over 54,000 leaf images across 38 distinct crop-disease classifications."
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TechRow(
                        icon = Icons.Filled.Lightbulb,
                        title = "MobileNetV2 Transfer Learning",
                        description = "Leverages the lightweight MobileNetV2 architecture, specialized for edge devices. Custom layers were added to adapt it to fine-grained botanical diagnostics."
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TechRow(
                        icon = Icons.Filled.Settings,
                        title = "INT8 Quantization",
                        description = "The TensorFlow model was post-quantized to 8-bit integers (INT8). This compressed the model to a compact ~3MB file, dramatically speeding up inference speed on mobile devices."
                    )

                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = LeafGreen,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = LeafGreen
        )
        Spacer(modifier = Modifier.weight(1f))
        // Decorative flower
        Icon(
            imageVector = Icons.Filled.LocalFlorist,
            contentDescription = null,
            tint = BotanicalGreen.copy(alpha = 0.4f),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun InfoCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PureWhite)
            .border(1.5.dp, PastelGreenCard, RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(PastelGreenCard),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = LeafGreen,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = LeafGreen
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextDark
            )
        }
    }
}

@Composable
fun TechRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BotanicalGreen,
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = LeafGreen
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TextDark
            )
        }
    }
}

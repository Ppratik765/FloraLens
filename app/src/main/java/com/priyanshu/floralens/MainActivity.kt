package com.priyanshu.floralens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.priyanshu.floralens.classifier.TFLiteImageClassifier
import com.priyanshu.floralens.ui.screens.HistoryTimelineScreen
import com.priyanshu.floralens.ui.screens.ScanDetailsScreen
import com.priyanshu.floralens.ui.screens.ScanScreen
import com.priyanshu.floralens.ui.screens.WelcomeScreen
import com.priyanshu.floralens.ui.theme.*
import com.priyanshu.floralens.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val classifier = TFLiteImageClassifier(this)
        viewModel.setClassifier(classifier)

        setContent {
            FloraLensTheme {
                FloraLensApp(viewModel)
            }
        }
    }
}

@Composable
fun FloraLensApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Hide bottom bar on scan screen and details screen
    val bottomBarVisible = currentRoute != "scan" && currentRoute?.startsWith("plant_details") != true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                FloraLensBottomBar(navController = navController, currentRoute = currentRoute)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("welcome") {
                Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    WelcomeScreen()
                }
            }
            composable("scan") {
                ScanScreen(
                    viewModel = viewModel,
                    onScanSaved = {
                        // After saving, navigate back or stay
                    }
                )
            }
            composable("history") {
                Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    HistoryTimelineScreen(
                        viewModel = viewModel,
                        onPlantClick = { plantId ->
                            navController.navigate("plant_details/$plantId")
                        }
                    )
                }
            }
            composable(
                route = "plant_details/{plantId}",
                arguments = listOf(navArgument("plantId") { type = NavType.StringType })
            ) { backStackEntry ->
                val plantId = backStackEntry.arguments?.getString("plantId") ?: return@composable
                ScanDetailsScreen(
                    viewModel = viewModel,
                    plantId = plantId,
                    onUpdateCondition = { id ->
                        viewModel.setPendingPlantId(id)
                        navController.navigate("scan") {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun FloraLensBottomBar(navController: NavHostController, currentRoute: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PremiumWhite)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 360.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // 1. Organic Canvas Vine Branches wrapping the border
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .zIndex(0f)
            ) {
                // Drawing vine lines winding around the edges
                val pathLeft = Path().apply {
                    moveTo(10.dp.toPx(), size.height - 10.dp.toPx())
                    quadraticTo(
                        -15.dp.toPx(), size.height * 0.5f,
                        15.dp.toPx(), 10.dp.toPx()
                    )
                }
                drawPath(
                    path = pathLeft,
                    color = YellowGreen,
                    style = Stroke(width = 3.dp.toPx())
                )

                val pathRight = Path().apply {
                    moveTo(size.width - 10.dp.toPx(), 10.dp.toPx())
                    quadraticTo(
                        size.width + 15.dp.toPx(), size.height * 0.5f,
                        size.width - 15.dp.toPx(), size.height - 10.dp.toPx()
                    )
                }
                drawPath(
                    path = pathRight,
                    color = YellowGreen,
                    style = Stroke(width = 3.dp.toPx())
                )
            }

            // 2. Leaf and Flower Icons peeking out (more in amount and more pronounced)
            // Left vine leaves
            Icon(
                imageVector = Icons.Filled.Eco,
                contentDescription = null,
                tint = YellowGreen,
                modifier = Modifier
                    .size(28.dp)
                    .offset(x = (-12).dp, y = (-12).dp)
                    .rotate(-45f)
                    .align(Alignment.TopStart)
                    .zIndex(2f)
            )
            Icon(
                imageVector = Icons.Filled.Eco,
                contentDescription = null,
                tint = LightGreen,
                modifier = Modifier
                    .size(22.dp)
                    .offset(x = (-16).dp, y = 24.dp)
                    .rotate(-105f)
                    .align(Alignment.CenterStart)
                    .zIndex(2f)
            )
            Icon(
                imageVector = Icons.Filled.LocalFlorist,
                contentDescription = null,
                tint = PastelPink,
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = (-10).dp, y = 48.dp)
                    .rotate(15f)
                    .align(Alignment.CenterStart)
                    .zIndex(2f)
            )

            // Right vine leaves and flowers
            Icon(
                imageVector = Icons.Filled.LocalFlorist,
                contentDescription = null,
                tint = PastelPink,
                modifier = Modifier
                    .size(26.dp)
                    .offset(x = 12.dp, y = (-12).dp)
                    .rotate(15f)
                    .align(Alignment.TopEnd)
                    .zIndex(2f)
            )
            Icon(
                imageVector = Icons.Filled.Eco,
                contentDescription = null,
                tint = YellowGreen,
                modifier = Modifier
                    .size(26.dp)
                    .offset(x = 14.dp, y = 18.dp)
                    .rotate(45f)
                    .align(Alignment.CenterEnd)
                    .zIndex(2f)
            )
            Icon(
                imageVector = Icons.Filled.Eco,
                contentDescription = null,
                tint = LightGreen,
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 10.dp, y = 42.dp)
                    .rotate(95f)
                    .align(Alignment.CenterEnd)
                    .zIndex(2f)
            )

            // Bottom edge leaves/flowers
            Icon(
                imageVector = Icons.Filled.Eco,
                contentDescription = null,
                tint = LightGreen,
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = (-60).dp, y = 10.dp)
                    .rotate(180f)
                    .align(Alignment.BottomCenter)
                    .zIndex(2f)
            )
            Icon(
                imageVector = Icons.Filled.Eco,
                contentDescription = null,
                tint = YellowGreen,
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 60.dp, y = 8.dp)
                    .rotate(120f)
                    .align(Alignment.BottomCenter)
                    .zIndex(2f)
            )
            Icon(
                imageVector = Icons.Filled.LocalFlorist,
                contentDescription = null,
                tint = PastelPink,
                modifier = Modifier
                    .size(16.dp)
                    .offset(x = 0.dp, y = 8.dp)
                    .align(Alignment.BottomCenter)
                    .zIndex(2f)
            )

            // 3. Floating Menu Bar card itself
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .shadow(elevation = 12.dp, shape = RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(PureWhite)
                    .border(2.dp, TurfGreen, RoundedCornerShape(24.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloraLensBottomBarItem(
                        icon = Icons.Filled.Home,
                        label = "Home",
                        isSelected = currentRoute == "welcome",
                        onClick = {
                            navController.navigate("welcome") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    FloraLensBottomBarItem(
                        icon = Icons.Filled.CameraAlt,
                        label = "Scan",
                        isSelected = currentRoute == "scan",
                        onClick = {
                            navController.navigate("scan") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    FloraLensBottomBarItem(
                        icon = Icons.Filled.History,
                        label = "History",
                        isSelected = currentRoute == "history",
                        onClick = {
                            navController.navigate("history") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.FloraLensBottomBarItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) LeafGreen else TextLight,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = if (isSelected) LeafGreen else TextLight,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
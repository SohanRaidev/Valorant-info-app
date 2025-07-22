package com.valorantinfo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.valorantinfo.app.ui.theme.ValorantRed

@Composable
fun MainScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = ValorantRed,
                contentColor = Color.White
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                        onNavigateToHome()
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(alpha = 0.6f)
                )
                
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") },
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                        onNavigateToSearch()
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(alpha = 0.6f)
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> {
                    // This will be handled by navigation - just placeholder
                }
                1 -> {
                    // This will be handled by navigation - just placeholder
                }
            }
        }
    }
}

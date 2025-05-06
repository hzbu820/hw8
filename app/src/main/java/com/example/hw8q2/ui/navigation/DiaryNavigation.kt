package com.example.hw8q2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hw8q2.data.PreferencesManager
import com.example.hw8q2.ui.screens.DiaryScreen
import com.example.hw8q2.ui.screens.SettingsScreen
import com.example.hw8q2.ui.theme.DiaryTheme

enum class DiaryScreen {
    Diary,
    Settings
}

@Composable
fun DiaryNavigation(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val preferencesManager = PreferencesManager(context)
    
    // Collect theme preferences
    val fontSize by preferencesManager.fontSizeFlow.collectAsState(initial = 16f)
    val isDarkMode by preferencesManager.darkModeFlow.collectAsState(initial = false)
    
    DiaryTheme(
        darkTheme = isDarkMode,
        fontSize = fontSize
    ) {
        NavHost(
            navController = navController,
            startDestination = DiaryScreen.Diary.name
        ) {
            composable(DiaryScreen.Diary.name) {
                DiaryScreen(
                    onSettingsClick = {
                        navController.navigate(DiaryScreen.Settings.name)
                    }
                )
            }
            
            composable(DiaryScreen.Settings.name) {
                SettingsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
} 
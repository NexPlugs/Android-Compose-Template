package com.example.androidcomposetemplate.ui

import androidx.compose.runtime.Composable
import com.example.androidcomposetemplate.navigation.AppNavHost
import com.example.core.designsystem.theme.AppTheme


@Composable
fun ApplicationMain() {
    AppTheme { AppNavHost() }
}
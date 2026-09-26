package com.danielimamura.apostadoranonimo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danielimamura.apostadoranonimo.ui.screens.LoginScreen
import com.danielimamura.apostadoranonimo.ui.screens.RegisterScreen

object AppRoutes {
    const val LOGIN = "login"
    const val REGISTER = "register"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN
    ) {
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onRegisterClick = { navController.navigate(AppRoutes.REGISTER) }
            )
        }
        composable(AppRoutes.REGISTER) {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() }
            )
        }
    }
}

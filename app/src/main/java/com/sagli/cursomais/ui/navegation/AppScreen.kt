package com.sagli.cursomais.ui.navigation

import androidx.compose.runtime.*
import com.sagli.cursomais.model.UserRole
import com.sagli.cursomais.ui.screens.AdminHomeScreen
import com.sagli.cursomais.ui.screens.LoginScreen
import com.sagli.cursomais.ui.screens.StudentHomeScreen

@Composable
fun AppScreen() {

    var userRole by remember {
        mutableStateOf<UserRole?>(null)
    }

    when (userRole) {

        null -> LoginScreen(
            onLoginSuccess = {
                userRole = it
            }
        )

        UserRole.ADMIN -> AdminHomeScreen()

        UserRole.STUDENT -> StudentHomeScreen()
    }
}
package com.sagli.cursomais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sagli.cursomais.ui.navigation.AppScreen
import com.sagli.cursomais.ui.theme.CursoMaisTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CursoMaisTheme {
                AppScreen()
            }
        }
    }
}
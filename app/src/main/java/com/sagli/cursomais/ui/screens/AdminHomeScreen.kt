package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminHomeScreen() {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            "Painel do Administrador",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Cadastrar Curso")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Cadastrar Categoria")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Cadastrar Aluno")
        }
    }
}
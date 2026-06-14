package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.service.AuthService
import com.sagli.cursomais.service.EnrollmentService

@Composable
fun StudentProfileScreen(
    onLogout: () -> Unit
) {

    val student = AuthService.getLoggedStudent()

    val enrollments =
        EnrollmentService
            .getEnrollmentsByStudent(
                student?.id ?: 0
            )

    val averageProgress =
        if (enrollments.isEmpty()) {

            0

        } else {

            enrollments
                .map {

                    it.completionPercentage
                }
                .average()
                .toInt()
        }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Text(

            text = "👤",

            style =
                MaterialTheme
                    .typography
                    .displayLarge
        )

        Spacer(
            Modifier.height(16.dp)
        )

        Text(

            text =
                student?.name ?: "Aluno",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Spacer(
            Modifier.height(24.dp)
        )

        Text(

            text =
                "Cursos inscritos: ${enrollments.size}"
        )

        Spacer(
            Modifier.height(8.dp)
        )

        LinearProgressIndicator(

            progress = {

                averageProgress / 100f
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            Modifier.height(8.dp)
        )

        Text(

            text =
                "Média de conclusão: $averageProgress%"
        )

        Spacer(
            Modifier.height(32.dp)
        )

        Button(

            onClick = {

                AuthService.logout()

                onLogout()
            }

        ) {

            Text("Sair")
        }
    }
}
package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus
import com.sagli.cursomais.service.AuthService
import com.sagli.cursomais.service.EnrollmentService

@Composable
fun StudentHomeScreen(
    modifier: Modifier = Modifier
) {

    val student = AuthService.getLoggedStudent()

    val courses = listOf(

        Course(
            id = 1,
            name = "Kotlin Básico",
            category = "Mobile",
            workload = 40,
            status = CourseStatus.incompleto
        ),

        Course(
            id = 2,
            name = "Jetpack Compose",
            category = "Mobile",
            workload = 60,
            status = CourseStatus.completo
        ),

        Course(
            id = 3,
            name = "Spring Boot",
            category = "Backend",
            workload = 80,
            status = CourseStatus.completo
        ),

        Course(
            id = 4,
            name = "SQL Essencial",
            category = "Banco de Dados",
            workload = 30,
            status = CourseStatus.planejado
        )
    )

    val enrollments = EnrollmentService.getEnrollmentsByStudent(

        student?.id ?: 0
    )

    LazyColumn(

        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        item {

            Text(

                text = "Olá, ${student?.name ?: "Aluno"} 👋",

                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "Continue aprendendo!",

                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(

                text = "Seus cursos",

                style = MaterialTheme.typography.titleLarge
            )
        }

        if (enrollments.isEmpty()) {

            item {

                Text(

                    text = "Você ainda não está inscrito em nenhum curso."
                )
            }

        } else {

            items(enrollments) { enrollment ->

                val course = courses.find {

                    it.id == enrollment.courseId
                }

                if (course != null) {

                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        elevation = CardDefaults.cardElevation(

                            defaultElevation = 4.dp
                        )
                    ) {

                        Column(

                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(

                                text = course.name,

                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(

                                modifier = Modifier.height(8.dp)
                            )

                            LinearProgressIndicator(

                                progress = {

                                    enrollment.completionPercentage / 100f
                                },

                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(

                                modifier = Modifier.height(8.dp)
                            )

                            Text(

                                text = "${enrollment.completionPercentage}% concluído"
                            )
                        }
                    }
                }
            }
        }
    }
}
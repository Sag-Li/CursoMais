package com.sagli.cursomais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus
import com.sagli.cursomais.service.CourseAnalyzer
import com.sagli.cursomais.ui.theme.CursoMaisTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CursoMaisTheme {

                val courses = remember {
                    listOf(
                        Course(1, "Kotlin Básico", "Mobile", 40, CourseStatus.completo),
                        Course(2, "Jetpack Compose", "Mobile", 60, CourseStatus.incompleto),
                        Course(3, "Banco de Dados", "Backend", 50, CourseStatus.planejado),
                        Course(4, "Spring Boot", "Backend", 80, CourseStatus.cancelado)
                    )
                }

                val filteredCourses = remember {
                    CourseAnalyzer.searchCourses(courses, "")
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->

                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "CursoMais",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Text(
                            text = "Lista de Cursos",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                        )

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            items(filteredCourses) { course ->

                                Column {

                                    Text(
                                        text = course.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(
                                        text = "Categoria: ${course.category}"
                                    )

                                    Text(
                                        text = "Carga horária: ${course.workload}h"
                                    )

                                    Text(
                                        text = "Status: ${course.status}"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
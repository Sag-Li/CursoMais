package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus
import com.sagli.cursomais.service.AuthService
import com.sagli.cursomais.service.CourseAnalyzer
import com.sagli.cursomais.service.EnrollmentService
import com.sagli.cursomais.ui.components.CourseCard

@Composable
fun CourseListScreen() {

    val student = AuthService.getLoggedStudent()

    val courses = remember {

        listOf(

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
    }

    var searchTerm by remember {

        mutableStateOf("")
    }

    var refreshTrigger by remember {

        mutableIntStateOf(0)
    }

    val filteredCourses = remember(

        searchTerm,
        refreshTrigger

    ) {

        CourseAnalyzer.searchCourses(

            courses,
            searchTerm
        )
    }

    Scaffold(

        modifier = Modifier.fillMaxSize()

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)

        ) {

            Text(

                text = "Explorar Cursos",

                style =
                    MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(

                value = searchTerm,

                onValueChange = {

                    searchTerm = it
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),

                label = {

                    Text(
                        "Pesquisar por curso ou categoria"
                    )
                },

                singleLine = true
            )

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp),

                contentPadding = PaddingValues(
                    bottom = 120.dp
                )

            ) {

                items(filteredCourses) { course ->

                    val enrollment = EnrollmentService
                        .getEnrollmentsByStudent(
                            student?.id ?: 0
                        )
                        .find {

                            it.courseId == course.id
                        }

                    CourseCard(

                        course = course,

                        progress =
                            (enrollment?.completionPercentage
                                ?: 0) / 100f,

                        isEnrolled =
                            enrollment != null,

                        onEnroll = {

                            student?.let { loggedStudent ->

                                EnrollmentService.enroll(

                                    studentId =
                                        loggedStudent.id,

                                    courseId =
                                        course.id
                                )

                                refreshTrigger++
                            }
                        },

                        onCancelEnrollment = {

                            student?.let { loggedStudent ->

                                EnrollmentService
                                    .cancelEnrollment(

                                        studentId =
                                            loggedStudent.id,

                                        courseId =
                                            course.id
                                    )

                                refreshTrigger++
                            }
                        },

                        onProgressChange = { progress ->

                            student?.let { loggedStudent ->

                                EnrollmentService
                                    .updateProgress(

                                        studentId =
                                            loggedStudent.id,

                                        courseId =
                                            course.id,

                                        progress =
                                            progress
                                    )

                                refreshTrigger++
                            }
                        }
                    )
                }
            }
        }
    }
}
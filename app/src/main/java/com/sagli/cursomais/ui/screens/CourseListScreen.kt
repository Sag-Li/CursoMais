package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus
import com.sagli.cursomais.service.CourseAnalyzer
import com.sagli.cursomais.ui.components.CourseCard

@Composable
fun CourseListScreen() {

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

    val filteredCourses = remember(
        searchTerm,
        courses
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
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "Explorar Cursos",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = searchTerm,
                onValueChange = {
                    searchTerm = it
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                label = {
                    Text("Pesquisar por curso ou categoria")
                },
                singleLine = true
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 16.dp
                )
            ) {

                items(filteredCourses) { course ->

                    CourseCard(
                        course = course
                    )
                }
            }
        }
    }
}
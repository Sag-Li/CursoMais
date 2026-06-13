package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus
import com.sagli.cursomais.ui.components.CourseCard

@Composable
fun StudentHomeScreen() {

    val courses = remember {

        listOf(

            Pair(
                Course(
                    1,
                    "Kotlin Básico",
                    "Mobile",
                    40,
                    CourseStatus.completo
                ),
                0.75f
            ),

            Pair(
                Course(
                    2,
                    "Jetpack Compose",
                    "Mobile",
                    60,
                    CourseStatus.incompleto
                ),
                0.30f
            )
        )
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Continue aprendendo",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(courses) {

                CourseCard(
                    course = it.first,
                    progress = it.second
                )
            }
        }
    }
}
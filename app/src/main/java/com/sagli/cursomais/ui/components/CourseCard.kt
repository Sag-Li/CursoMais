package com.sagli.cursomais.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course

@Composable
fun CourseCard(
    course: Course,
    progress: Float = 0f
) {

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
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Categoria: ${course.category}"
            )

            Text(
                text = "Carga horária: ${course.workload} horas"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "${(progress * 100).toInt()}% concluído"
            )
        }
    }
}
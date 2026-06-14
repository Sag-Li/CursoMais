package com.sagli.cursomais.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagli.cursomais.model.Course

@Composable
fun CourseCard(
    course: Course,
    progress: Float = 0f,
    isEnrolled: Boolean = false,
    onEnroll: () -> Unit = {},
    onCancelEnrollment: () -> Unit = {},
    onProgressChange: (Int) -> Unit = {}
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

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (isEnrolled) {

                Slider(

                    value = progress,

                    onValueChange = {

                        onProgressChange(

                            (it * 100).toInt()
                        )
                    }
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                OutlinedButton(

                    onClick = onCancelEnrollment,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Cancelar inscrição")
                }

            } else {

                Button(

                    onClick = onEnroll,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Inscrever-se")
                }
            }
        }
    }
}
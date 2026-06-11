package com.sagli.cursomais.model

data class Enrollment(

    val studentId: Int,

    val studentName: String,

    val courseId: Int,

    val completionPercentage: Int,

    val finalGrade: Double
)
package com.sagli.cursomais.model

data class CourseSummary(

    val courseId: Int,

    val name: String,

    val category: String,

    val studentCount: Int,

    val averageGrade: Double,

    val averageCompletion: Double
)
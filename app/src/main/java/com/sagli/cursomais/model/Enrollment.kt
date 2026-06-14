package com.sagli.cursomais.model

data class Enrollment(

    val studentId: Int,

    val courseId: Int,

    var completionPercentage: Int = 0
)
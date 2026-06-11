package com.sagli.cursomais.model

data class Course(

    val id: Int,

    val name: String,

    val category: String,

    val workload: Int,

    val status: CourseStatus
)
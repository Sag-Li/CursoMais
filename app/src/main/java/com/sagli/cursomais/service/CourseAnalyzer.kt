package com.sagli.cursomais.service

import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus

object CourseAnalyzer {

    fun filterCourses(
        courses: List<Course>,
        category: String?,
        status: CourseStatus?
    ): List<Course> {

        return courses.filter { course ->

            val categoryMatches =
                category == null ||
                        course.category.equals(category, ignoreCase = true)

            val statusMatches =
                status == null ||
                        course.status == status

            categoryMatches && statusMatches
        }
    }

    fun searchCourses(
        courses: List<Course>,
        term: String
    ): List<Course> {

        val cleanTerm = term.trim()

        if (cleanTerm.isBlank()) {
            return courses
        }

        return courses.filter {

            it.name.contains(
                cleanTerm,
                ignoreCase = true
            ) ||

                    it.category.contains(
                        cleanTerm,
                        ignoreCase = true
                    )
        }
    }
}
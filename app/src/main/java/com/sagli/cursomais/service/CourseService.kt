package com.sagli.cursomais.service

import com.sagli.cursomais.model.Course
import com.sagli.cursomais.model.CourseStatus

class CourseService {

    private val courses = mutableListOf<Course>()

    fun addCourse(course: Course) {
        courses.add(course)
    }

    fun getAllCourses(): List<Course> {
        return courses
    }

    fun findByCategory(category: String): List<Course> {
        return courses.filter {
            it.category.equals(category, ignoreCase = true)
        }
    }

    fun findByStatus(status: CourseStatus): List<Course> {
        return courses.filter {
            it.status == status
        }
    }
}
package com.sagli.cursomais.service

import com.sagli.cursomais.model.Enrollment

class EnrollmentService {

    private val enrollments = mutableListOf<Enrollment>()

    fun addEnrollment(enrollment: Enrollment) {
        enrollments.add(enrollment)
    }

    fun getAllEnrollments(): List<Enrollment> {
        return enrollments
    }

    fun getEnrollmentsByCourse(courseId: Int): List<Enrollment> {
        return enrollments.filter {
            it.courseId == courseId
        }
    }
}
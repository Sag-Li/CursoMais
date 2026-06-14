package com.sagli.cursomais.service

import com.sagli.cursomais.model.Enrollment

object EnrollmentService {

    private val enrollments = mutableListOf<Enrollment>()

    fun enroll(
        studentId: Int,
        courseId: Int
    ) {

        val alreadyExists = enrollments.any {

            it.studentId == studentId &&
                    it.courseId == courseId
        }

        if (!alreadyExists) {

            enrollments.add(

                Enrollment(
                    studentId = studentId,
                    courseId = courseId
                )
            )
        }
    }

    fun cancelEnrollment(
        studentId: Int,
        courseId: Int
    ) {

        enrollments.removeIf {

            it.studentId == studentId &&
                    it.courseId == courseId
        }
    }

    fun updateProgress(
        studentId: Int,
        courseId: Int,
        progress: Int
    ) {

        enrollments.find {

            it.studentId == studentId &&
                    it.courseId == courseId
        }?.completionPercentage = progress
    }

    fun getEnrollmentsByStudent(
        studentId: Int
    ): List<Enrollment> {

        return enrollments.filter {

            it.studentId == studentId
        }
    }
}
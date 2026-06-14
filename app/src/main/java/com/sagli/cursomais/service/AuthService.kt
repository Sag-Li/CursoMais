package com.sagli.cursomais.service

import com.sagli.cursomais.model.Student
import com.sagli.cursomais.model.UserRole

object AuthService {

    private const val ADMIN_EMAIL = "sara@adm.com"
    private const val ADMIN_PASSWORD = "1234"

    private var loggedStudent: Student? = null

    private val students = mutableListOf(

        Student(
            id = 1,
            name = "Sara",
            email = "sara@aluno.com",
            password = "1234"
        )
    )

    fun login(
        email: String,
        password: String
    ): UserRole? {

        if (
            email == ADMIN_EMAIL &&
            password == ADMIN_PASSWORD
        ) {

            loggedStudent = null

            return UserRole.ADMIN
        }

        val student = students.find {

            it.email.equals(
                email,
                ignoreCase = true
            ) &&

                    it.password == password
        }

        return if (student != null) {

            loggedStudent = student

            UserRole.STUDENT

        } else {

            null
        }
    }

    fun addStudent(
        student: Student
    ): Boolean {

        if (
            !student.email.endsWith(
                "@aluno.com"
            )
        ) {

            return false
        }

        students.add(student)

        return true
    }

    fun getStudents(): List<Student> {

        return students
    }

    fun getLoggedStudent(): Student? {

        return loggedStudent
    }

    fun logout() {

        loggedStudent = null
    }
}
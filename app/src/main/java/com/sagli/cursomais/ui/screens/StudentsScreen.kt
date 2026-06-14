package com.sagli.cursomais.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sagli.cursomais.model.StudentTab

@Composable
fun StudentScreen(
    onLogout: () -> Unit
) {

    var selectedTab by remember {

        mutableStateOf(
            StudentTab.home
        )
    }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected =
                        selectedTab ==
                                StudentTab.home,

                    onClick = {

                        selectedTab =
                            StudentTab.home
                    },

                    icon = {

                        Text("🏠")
                    },

                    label = {

                        Text("Home")
                    }
                )

                NavigationBarItem(

                    selected =
                        selectedTab ==
                                StudentTab.courses,

                    onClick = {

                        selectedTab =
                            StudentTab.courses
                    },

                    icon = {

                        Text("📚")
                    },

                    label = {

                        Text("Cursos")
                    }
                )

                NavigationBarItem(

                    selected =
                        selectedTab ==
                                StudentTab.profile,

                    onClick = {

                        selectedTab =
                            StudentTab.profile
                    },

                    icon = {

                        Text("👤")
                    },

                    label = {

                        Text("Perfil")
                    }
                )
            }
        }

    ) { padding ->

        when (selectedTab) {

            StudentTab.home ->

                StudentHomeScreen(

                    modifier =
                        Modifier.padding(
                            padding
                        )
                )

            StudentTab.courses ->

                CourseListScreen()

            StudentTab.profile ->

                StudentProfileScreen(

                    onLogout =
                        onLogout
                )
        }
    }
}
package org.saavatech.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import org.saavatech.project.cache.TravenorSession
import org.saavatech.project.presentation.HomeScren
import org.saavatech.project.presentation.feature.LoadingScreen
import org.saavatech.project.presentation.feature.auth.LoginScreen
import org.saavatech.project.presentation.feature.auth.RegisterScreen
import org.saavatech.project.presentation.navigation.NavRoutes

@Composable
@Preview
fun App() {
    MaterialTheme {
        val session: TravenorSession=getKoin().get()

        println(session)
        val currentScreen = remember { mutableStateOf<String?>(null)}
        val coroutineScope = rememberCoroutineScope()
        coroutineScope.launch{
            if (session.getToken() != null){
                currentScreen.value = NavRoutes.Home.route
            }else{
                currentScreen.value = NavRoutes.Login.route
            }
        }

        val navController = rememberNavController()
        currentScreen.value?.let {
            NavHost(navController, startDestination = it){
                composable(NavRoutes.Login.route){
                    LoginScreen(navController)
                }
                composable(NavRoutes.Register.route){
                    RegisterScreen(navController)
                }
                composable(NavRoutes.Home.route){
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Home")
                    }
                }

                composable(NavRoutes.Loading.route){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CircularProgressIndicator()
                        Text("Loading")
                    }
                }
            }
        }


    }
}





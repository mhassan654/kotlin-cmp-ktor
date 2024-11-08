package org.saavatech.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import org.saavatech.project.cache.TravenorSession
import org.saavatech.project.presentation.HomeScren
import org.saavatech.project.presentation.feature.LoadingScreen
import org.saavatech.project.presentation.feature.auth.LoginScreen
import org.saavatech.project.presentation.feature.auth.RegisterScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val session: TravenorSession=getKoin().get()
        val currentScreen = remember { mutableStateOf("loading")}
        val coroutineScope = rememberCoroutineScope()
        coroutineScope.launch{
            if (session.getToken()!=null){
                currentScreen.value ="home"
            }else{
                currentScreen.value ="login"
            }
        }

        if (currentScreen.value =="loading"){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CircularProgressIndicator()
                Text("Loading")
            }
        }else{
            when (currentScreen.value){
                "home"->HomeScren()
                "login"->LoginScreen()
                "register"->RegisterScreen()
            }
        }

    }
}





package org.saavatech.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saavatech.project.presentation.feature.register.RegisterScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
       RegisterScreen()
    }
}
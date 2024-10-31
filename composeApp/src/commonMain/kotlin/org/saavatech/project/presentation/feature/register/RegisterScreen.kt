package org.saavatech.project.ui.feature.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(){
    Surface(modifier = Modifier.fillMaxSize()){
        Column(
            Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var name by remember{ mutableStateOf("") }
            var email by remember{ mutableStateOf("") }
            var password by remember{ mutableStateOf("") }
            var confirmPassword by remember{ mutableStateOf("") }

            Text(text = "Register",
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
                    , fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = email,
                onValueChange = {email=it},
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = {name=it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = {password=it},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {confirmPassword=it},
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                enabled = email.isNotEmpty()
                        &&password.isNotEmpty()
                        && confirmPassword.isNotEmpty()
                        &&name.isNotEmpty()
                        && password==confirmPassword

            ){
                Text("Register")
            }
        }
    }
}

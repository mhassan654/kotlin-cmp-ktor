package org.saavatech.project.data.Requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email:String,
    val password:String,
    val name:String,
)

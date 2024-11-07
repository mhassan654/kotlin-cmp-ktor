package org.saavatech.project.data.Requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val email: String, val password:String)

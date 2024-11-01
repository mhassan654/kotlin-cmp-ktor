package org.saavatech.project.data.Responses

import kotlinx.serialization.Serializable
import org.saavatech.project.data.models.User
@Serializable
data class RegisterResponse(val user: User, val token: String)

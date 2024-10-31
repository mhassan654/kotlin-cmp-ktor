package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(val user: User,val token: String)

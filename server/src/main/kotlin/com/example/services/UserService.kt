package com.example.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.UserRequest
import com.example.models.UserResponse
import com.example.plugins.jwtAudience
import com.example.plugins.jwtDomain
import com.example.plugins.jwtSecret
import com.example.repositories.UserRepository
import java.security.MessageDigest
import java.util.Date

class UserService(val _userRepository: UserRepository) {
  suspend  fun createUser(userRequest: UserRequest): UserResponse{
      val userExists = _userRepository.findUserByEmail(userRequest.email)
      if (userExists != null){
          throw IllegalArgumentException("user with email ${userRequest.email} already exists!")
      }
        val user = _userRepository.register(userRequest.copy(password = hashPassword(userRequest.password)))
      return UserResponse(user, generateJwtToken(user.email,user.id))
    }

    suspend fun loginUser(email:String,password: String): UserResponse{
        val hashedPassword = hashPassword(password)
        val user= _userRepository.loginIn(email,hashedPassword)?: throw IllegalArgumentException("Invalid credentials")
        return UserResponse(user, generateJwtToken(user.email,user.id))

    }

    //configure jwt token
    fun generateJwtToken(email:String,id:Long): String{
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .withClaim("email",email)
            .withClaim("userId",id)
            .withExpiresAt(Date(System.currentTimeMillis() + (24*60*60*60*1000L)))
            .sign(Algorithm.HMAC256(jwtSecret))
    }

//    encrypt passwor string
    private fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return  md.digest(password.toByteArray()).fold(""){str,it->str + "%02x".format(it)}
    }
}
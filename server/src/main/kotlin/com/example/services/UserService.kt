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
      print( user)
      return UserResponse(user, generateJwtToken(user.email))
    }

    //configure jwt token
    fun generateJwtToken(email:String): String{
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .withClaim("email",email)
            .withExpiresAt(Date(System.currentTimeMillis() + (24*60*60*60*1000L)))
            .sign(Algorithm.HMAC256(jwtSecret))
    }

//    encrypt passwor string
    private fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return  md.digest(password.toByteArray()).fold(""){str,it->str + "%02x".format(it)}
    }
}
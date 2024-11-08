package com.example.routes

import com.example.models.LoginRequest
import com.example.models.UserRequest
import com.example.services.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.userRoutes(userService: UserService){
    route("/users"){
        post("/login"){
            val request = call.receive<LoginRequest>()
            val response = userService.loginUser(request.email,request.password)
            call.respond(HttpStatusCode.OK, response)
        }

        post("/register"){
            val request = call.receive<UserRequest>()
            val response = userService.createUser(request)
            call.respond(HttpStatusCode.OK,response)
        }
    }
}
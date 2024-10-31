package com.example.plugins

import com.example.repositories.UserRepository
import com.example.routes.userRoutes
import com.example.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "$cause")
//            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        authenticate("auth-jwt") {
           get("/auth"){
               call.respondText("authenticated")
           }
        }

//        auth routes
        userRoutes(UserService(UserRepository()))
    }
}

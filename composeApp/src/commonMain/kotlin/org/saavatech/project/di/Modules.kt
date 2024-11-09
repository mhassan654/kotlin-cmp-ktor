package org.saavatech.project.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.saavatech.project.cache.TravenorSession
import org.saavatech.project.data.NetworkService
import org.saavatech.project.viewModal.LoginViewModel
import org.saavatech.project.viewModal.RegisterViewModal

//expect val platformModule: Module
expect val platformModule: Module

val shareModule= module {
    single {
        HttpClient(CIO){
        install(Logging){
            level = LogLevel.ALL
            logger = object : Logger{
                override fun log(message: String) {
                    println("BackeEndHandler:$message")
                }
            }
        }
            install(ContentNegotiation){
                json(
                    Json {
                        prettyPrint=true
                        isLenient=true
                        ignoreUnknownKeys=true
                    }
                )
            }
        }
    }

    single {
        NetworkService(get())
    }

    viewModel {
        RegisterViewModal(get(),get())
    }

    viewModel {
        LoginViewModel(get(),get())
    }
    single<TravenorSession>{
        TravenorSession(get())
    }
}
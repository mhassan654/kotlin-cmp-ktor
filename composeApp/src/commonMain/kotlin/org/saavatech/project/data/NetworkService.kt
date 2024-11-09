package org.saavatech.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException
import org.saavatech.project.data.Requests.LoginRequest
import org.saavatech.project.data.Requests.RegisterRequest
import org.saavatech.project.data.Responses.RegisterResponse

expect val baseUrl: String
class NetworkService(val httpClient: HttpClient) {
//    val baseUrl= "http://192.168.0.119:8080"

    suspend fun register(registerRequest: RegisterRequest): ResultResponse<RegisterResponse>{
        return makeWebRequest<RegisterResponse>("$baseUrl/users/auth",HttpMethod.Post, body = registerRequest)
    }

    suspend fun login(request: LoginRequest): ResultResponse<RegisterResponse>{
        return makeWebRequest<RegisterResponse>(
            "$baseUrl/users/login",
            HttpMethod.Post,
            body = request
        )
    }

    suspend inline fun <reified T> makeWebRequest(
        url:String,
        method: HttpMethod,
        body:Any?=null,
        headers: Map<String,String> = emptyMap(),
        parameters: Map<String,String> = emptyMap()
    ):ResultResponse<T> {
        return try {
            val response = httpClient.request(url){
                method.also { this.method = it } // this.method = method
                headers.forEach {
                    (key,value)->this.headers.append(key,value)
                }
                parameters.forEach {
                    (key,value)->this.parameter(key,value)
                }

                if (body!=null){
                    this.setBody(body)
                }
                contentType(ContentType.Application.Json)
            }.body<T>()
            ResultResponse.Success(response)

        }catch (e: ClientRequestException){
            ResultResponse.Error(e)
        }catch (e: ServerResponseException){
            ResultResponse.Error(e)
        }catch (e: IOException){
            ResultResponse.Error(e)
        }catch (e: Exception){
            ResultResponse.Error(e)
        }
    }
}

sealed class ResultResponse<out T>{
    data class Success<out T>(val value: T):ResultResponse<T>()
    data class Error(val e: Exception):ResultResponse<Nothing>()
}
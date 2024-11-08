package org.saavatech.project.viewModal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.saavatech.project.cache.TravenorSession
import org.saavatech.project.data.NetworkService
import org.saavatech.project.data.Requests.LoginRequest
import org.saavatech.project.data.ResultResponse

class LoginViewModel(val networkService: NetworkService,private val session: TravenorSession): ViewModel() {
    val _uiState = MutableStateFlow<LoginState>(LoginState.Nothing)
    val uiState =_uiState.asStateFlow()

    fun login(email:String, password: String){
        _uiState.value = LoginState.Loading
        viewModelScope.launch{
                val result = networkService.login(LoginRequest(email,password))
            when (result){
                is ResultResponse.Success->{
                    _uiState.value = LoginState.Success
                    session.saveToken(result.value.token)
                }

                is ResultResponse.Error -> {
                    _uiState.value = LoginState.Error(result.e.message?:"An error occured")
                }
            }
        }
    }

    fun retry() {
        _uiState.value = LoginState.Nothing
    }
}

sealed class LoginState{
    object Nothing: LoginState()
    object Loading: LoginState()
    object Success: LoginState()
    data class Error(val message: String): LoginState()
}
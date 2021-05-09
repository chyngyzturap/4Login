package com.pharos.forlogin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pharos.forlogin.data.network.Resource
import com.pharos.forlogin.data.repository.AuthRepository
import com.pharos.forlogin.data.responses.LoginResponse
import com.pharos.forlogin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository){

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(username, password)
    }

    suspend fun saveAuthToken(tokenAccess: String/*, tokenRefresh: String*/){
        repository.saveAuthToken(tokenAccess/*, tokenRefresh*/)
    }
}
package com.pharos.forlogin.data.repository

import com.pharos.forlogin.data.UserPreferences
import com.pharos.forlogin.data.network.AuthApi

class AuthRepository (
    private val api: AuthApi,
    private val preferences: UserPreferences
        ) : BaseRepository(){

            suspend fun login(
                username: String,
                password: String
            ) = safeApiCall {
                api.login(username, password)
            }

    suspend fun saveAuthToken(tokenAccess: String/*, tokenRefresh: String*/){
        preferences.saveAuthToken(tokenAccess/*, tokenRefresh*/)
    }
        }
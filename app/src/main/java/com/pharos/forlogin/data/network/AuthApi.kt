package com.pharos.forlogin.data.network

import com.pharos.forlogin.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/api/users/login/")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : LoginResponse
}
package com.pharos.forlogin.data.responses

data class LoginResponse(
    val access: String,
    val refresh: String,
    val user: User
)
package com.pharos.forlogin.data.responses

data class User(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val role: Int,
    val username: String
)
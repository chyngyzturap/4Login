package com.pharos.forlogin.data.responses

data class Rooms(
    val status: Boolean,
    val title: String
) {
    override fun toString(): String {
        return "Rooms(status=$status, title='$title')"
    }
}
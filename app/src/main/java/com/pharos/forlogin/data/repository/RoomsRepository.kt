package com.pharos.forlogin.data.repository

import com.pharos.forlogin.data.network.RoomsApi

class RoomsRepository(
    private val api: RoomsApi
) : BaseRepository() {

    suspend fun getRooms() = safeApiCall {
        api.getRooms()
    }
}
package com.pharos.forlogin.data.network

import androidx.lifecycle.LiveData
import com.pharos.forlogin.data.responses.Rooms
import com.pharos.forlogin.data.responses.RoomsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RoomsApi {

    @GET("/api/rooms/status/?date=2021-06-05")
    suspend fun getRooms(): List<Rooms>

    @POST("logout")
    suspend fun logout(): ResponseBody

}
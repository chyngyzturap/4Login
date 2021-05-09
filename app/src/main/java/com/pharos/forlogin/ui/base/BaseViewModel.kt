package com.pharos.forlogin.ui.base

import androidx.lifecycle.ViewModel
import com.pharos.forlogin.data.network.RoomsApi
import com.pharos.forlogin.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
    suspend fun logout(api: RoomsApi) = repository.logout(api)
}
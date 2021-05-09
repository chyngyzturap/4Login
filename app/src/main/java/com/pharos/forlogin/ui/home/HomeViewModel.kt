package com.pharos.forlogin.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pharos.forlogin.data.network.Resource
import com.pharos.forlogin.data.repository.RoomsRepository
import com.pharos.forlogin.data.responses.Rooms
import com.pharos.forlogin.data.responses.RoomsResponse
import com.pharos.forlogin.ui.base.BaseViewModel
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: RoomsRepository
) : BaseViewModel(repository) {

    private val _rooms: MutableLiveData<Resource<List<Rooms>>> = MutableLiveData()
    val rooms: MutableLiveData<Resource<List<Rooms>>>
        get() = _rooms

    fun getRooms() = viewModelScope.launch {
        _rooms.value = Resource.Loading
        _rooms.value = repository.getRooms()
    }
}
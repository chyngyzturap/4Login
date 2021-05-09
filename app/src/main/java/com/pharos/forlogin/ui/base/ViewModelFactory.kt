package com.pharos.forlogin.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pharos.forlogin.data.repository.AuthRepository
import com.pharos.forlogin.data.repository.BaseRepository
import com.pharos.forlogin.data.repository.RoomsRepository
import com.pharos.forlogin.ui.auth.AuthViewModel
import com.pharos.forlogin.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as RoomsRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}
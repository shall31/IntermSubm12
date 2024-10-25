package com.dicoding.picodiploma.intermsubm1_2.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.intermsubm1_2.repository.AuthRepository
import com.dicoding.picodiploma.intermsubm1_2.ui.auth.LoginViewModel
import com.dicoding.picodiploma.intermsubm1_2.ui.auth.RegisterViewModel
//import com.dicoding.picodiploma.intermsubm1_2.repository.AuthRepository
//import com.dicoding.picodiploma.intermsubm1_2.ui.auth.LoginViewModel
//import com.dicoding.picodiploma.intermsubm1_2.ui.auth.RegisterViewModel
import com.dicoding.picodiploma.intermsubm1_2.ui.detail.DetailViewModel
import com.dicoding.picodiploma.intermsubm1_2.ui.map.MapsViewModel

//import com.dicoding.picodiploma.intermsubm1_2.ui.main.MainViewModel
//import com.dicoding.picodiploma.intermsubm1_2.ui.map.MapsViewModel
//
class ViewModelFactory private constructor(
    private val authRepository: AuthRepository
): ViewModelProvider.Factory{

        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(): ViewModelFactory =  instance ?: synchronized(this) {
                instance ?: ViewModelFactory(AuthRepository.getInstance())
            }
        }
//
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(authRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}
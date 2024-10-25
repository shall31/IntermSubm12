package com.dicoding.picodiploma.intermsubm1_2.ui.main

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.intermsubm1_2.repository.MainRepository
import com.dicoding.picodiploma.intermsubm1_2.response.ListStoryItem
import com.dicoding.picodiploma.intermsubm1_2.utils.Injection

class MainViewModel(mainRepository: MainRepository) : ViewModel() {
    val quote: LiveData<PagingData<ListStoryItem>> =
        mainRepository.getQuote().cachedIn(viewModelScope)

}

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
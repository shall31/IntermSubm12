package com.dicoding.picodiploma.intermsubm1_2.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dicoding.picodiploma.intermsubm1_2.api.ApiService
import com.dicoding.picodiploma.intermsubm1_2.response.ListStoryItem

class MainRepository(private val apiService: ApiService) {
    fun getQuote(): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                MainPagingSource(apiService)
            }
        ).liveData
    }
}
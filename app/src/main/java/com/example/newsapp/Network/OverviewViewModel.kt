package com.example.newsapp.Network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.DataSource.ItemNews
import com.example.newsapp.NewsApi
import com.example.newsapp.Repository.Repository
import kotlinx.coroutines.launch

class OverviewViewModel(private val repository: Repository) : ViewModel() {

    private val _status = MutableLiveData<String>()
    private val _news = MutableLiveData<ItemNews>()

    val status: LiveData<String> = _status
    val news: LiveData<ItemNews> = _news

    init {
        getNewsModel()
    }

    private fun getNewsModel() {
        viewModelScope.launch {
            try {
                val response = repository.getNews()
                _news.value = response
                /*
                val listResult = NewsApi.retrofitService.getNews()
                _status.value = "${listResult}"
                _news.value = listResult*/
            }
            catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }

        }
    }
}
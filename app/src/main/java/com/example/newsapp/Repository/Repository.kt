package com.example.newsapp.Repository

import com.example.newsapp.DataSource.ItemNews
import com.example.newsapp.NewsApi
import retrofit2.http.GET

class Repository {

    suspend  fun getNews() : ItemNews{
        return  NewsApi.retrofitService.getNews()
    }
}
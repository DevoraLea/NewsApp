package com.example.newsapp

import com.example.newsapp.DataSource.ItemNews
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
const val BASE_URL = "http://api.mediastack.com/v1/"
const val API_KEY = "844686cf4e1746276e557bfdcdef7f07"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiInterface {

    @GET("news?access_key=844686cf4e1746276e557bfdcdef7f07")
    suspend  fun getNews() : ItemNews

    @GET("sources?access_key=${API_KEY}")
    suspend  fun getNewsSources() : ItemNews
}


object NewsApi {
    val retrofitService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java) }
}


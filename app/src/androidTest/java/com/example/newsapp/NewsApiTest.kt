package com.example.newsapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsApiTest : BseTest() {



        private lateinit var service: ApiInterface

        @Before
        fun setup() {
            val url = mockWebServer.url("/")
            service = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    ))
                .build()
                .create(ApiInterface::class.java)
        }

        @Test
        fun api_service() {
            enqueue("mars_photos.json")
            runBlocking {
                val apiResponse = service.getNews()

                Assert.assertNotNull(apiResponse)
                Assert.assertTrue("The list was empty", apiResponse.data.isNotEmpty())
                Assert.assertEquals("The id's did not match", "424905", apiResponse.data[0].author)
            }
        }

}
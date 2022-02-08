package com.example.newsapp.DataSource

data class Pagination(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val total: Int
)
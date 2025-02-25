package com.example.deeplinking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Post(val userId: Int, val id: Int, val title: String, val body: String)

interface ApiService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: String): Post
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)

suspend fun fetchPostDetails(postId: String): Post? {
    return try {
        apiService.getPost(postId)
    } catch (e: Exception) {
        null
    }
}


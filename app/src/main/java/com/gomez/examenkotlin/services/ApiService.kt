package com.gomez.examenkotlin.services

import com.gomez.examenkotlin.models.PostResponse
import com.gomez.examenkotlin.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("profile")
    suspend fun getProfile(): Response<UserResponse>

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}
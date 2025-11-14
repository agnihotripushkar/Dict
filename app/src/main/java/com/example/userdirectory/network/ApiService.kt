package com.example.userdirectory.network

import com.example.userdirectory.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

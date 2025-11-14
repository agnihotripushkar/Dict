package com.example.userdirectory.repository

import android.util.Log
import com.example.userdirectory.database.UserDao
import com.example.userdirectory.model.User
import com.example.userdirectory.network.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao, private val apiService: ApiService) {

    val users: Flow<List<User>> = userDao.getUsers()

    suspend fun refreshUsers() {
        try {
            val users = apiService.getUsers()
            userDao.insertAll(users)
        } catch (e: Exception) {
            Log.e("UserRepository", "Failed to refresh users", e)
        }
    }

    fun searchUsers(query: String): Flow<List<User>> {
        return userDao.searchUsers("%$query%")
    }
}
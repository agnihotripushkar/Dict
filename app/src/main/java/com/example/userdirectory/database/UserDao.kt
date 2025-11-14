package com.example.userdirectory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userdirectory.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE name LIKE :query OR email LIKE :query")
    fun searchUsers(query: String): Flow<List<User>>
}
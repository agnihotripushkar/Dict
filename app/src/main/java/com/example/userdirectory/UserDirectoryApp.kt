package com.example.userdirectory

import android.app.Application
import com.example.userdirectory.database.AppDatabase
import com.example.userdirectory.network.RetrofitInstance
import com.example.userdirectory.repository.UserRepository

class UserDirectoryApp : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao(), RetrofitInstance.api) }
}
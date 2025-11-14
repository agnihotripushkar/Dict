package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.userdirectory.ui.UserScreen
import com.example.userdirectory.ui.theme.UserDirectoryTheme
import com.example.userdirectory.viewmodel.UserViewModel
import com.example.userdirectory.viewmodel.factory.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserDirectoryApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserDirectoryTheme {
                UserScreen(viewModel = viewModel)
            }
        }
    }
}
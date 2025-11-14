package com.example.userdirectory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.userdirectory.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Directory") }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                label = { Text("Search by name or email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) { user ->
                    UserListItem(user = user)
                }
            }
        }
    }
}
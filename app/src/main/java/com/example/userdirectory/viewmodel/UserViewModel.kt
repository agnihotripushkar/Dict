package com.example.userdirectory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.model.User
import com.example.userdirectory.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery.flatMapLatest { query ->
                if (query.isEmpty()) {
                    userRepository.users
                } else {
                    userRepository.searchUsers(query)
                }
            }.collect { userList ->
                _users.value = userList
            }
        }
        refreshUsers()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun refreshUsers() {
        viewModelScope.launch {
            userRepository.refreshUsers()
        }
    }
}
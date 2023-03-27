package com.example.userwithhilt_retrofit.ui.users

import com.example.userwithhilt_retrofit.domain.model.UserItem

data class UsersViewState(
    var listOfUsers: List<UserItem>? = null,
    var message: String = ""
)

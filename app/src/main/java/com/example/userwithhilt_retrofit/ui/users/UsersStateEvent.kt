package com.example.userwithhilt_retrofit.ui.users

sealed class UsersStateEvent {
    object GetUsersEvent : UsersStateEvent()
    object None : UsersStateEvent()
}
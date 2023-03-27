package com.example.userwithhilt_retrofit.ui.users.noteList

sealed class UsersEvent {
    object GetUsers: UsersEvent()
}
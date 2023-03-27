package com.example.userwithhilt_retrofit.domain.repository

import com.example.userwithhilt_retrofit.domain.util.DataState
import com.example.userwithhilt_retrofit.ui.users.UsersViewState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<DataState<UsersViewState>>
}
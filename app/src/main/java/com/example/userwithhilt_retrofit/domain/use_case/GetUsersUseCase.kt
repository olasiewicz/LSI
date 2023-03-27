package com.example.userwithhilt_retrofit.domain.use_case

import com.example.userwithhilt_retrofit.domain.repository.UserRepository
import com.example.userwithhilt_retrofit.domain.util.DataState
import com.example.userwithhilt_retrofit.ui.users.UsersViewState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase
@Inject constructor(
    private val repository: UserRepository
) {
    fun getUsers(): Flow<DataState<UsersViewState>> {
        return repository.getUsers()
    }
}
package com.example.userwithhilt_retrofit.data.repository

import com.example.userwithhilt_retrofit.data.datasource.cache.UserDao
import com.example.userwithhilt_retrofit.data.datasource.network.UserApiService
import com.example.userwithhilt_retrofit.domain.repository.UserRepository
import com.example.userwithhilt_retrofit.domain.util.DataState
import com.example.userwithhilt_retrofit.ui.users.UsersViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val service: UserApiService,
    private val dao: UserDao
) : UserRepository {
    override fun getUsers(): Flow<DataState<UsersViewState>> = flow {

        try {
            emit(DataState.loading())
            try {
                val users = service.getUsers()
                users.onEach {user ->
                    user.id?.let {
                        dao.insertUser(service.getUserDetails(it))
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            // query the cache
            val cacheUsers = dao.getUsers()

            emit(
                DataState.success(
                    UsersViewState(
                        listOfUsers = cacheUsers,
                        message = "SUCCESS"
                    )
                )
            )
        } catch (e: Exception) {
            emit(
                DataState.error(
                    message = "ERROR"
                )
            )
        }
    }

}


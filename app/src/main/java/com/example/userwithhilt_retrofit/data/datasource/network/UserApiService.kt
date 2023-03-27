package com.example.userwithhilt_retrofit.data.datasource.network

import com.example.userwithhilt_retrofit.domain.model.UserBasic
import com.example.userwithhilt_retrofit.domain.model.UserItem
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users")
    suspend fun getUsers(): List<UserBasic>

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") id: Int
    ): UserItem
}

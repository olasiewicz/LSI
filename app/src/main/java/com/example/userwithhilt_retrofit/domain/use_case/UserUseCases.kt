package com.example.userwithhilt_retrofit.domain.use_case

import javax.inject.Inject

data class UserUseCases
@Inject constructor(
    val getUsersUseCase: GetUsersUseCase
)

package com.example.userwithhilt_retrofit.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userwithhilt_retrofit.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _viewState: MutableLiveData<UsersViewState> = MutableLiveData()

    val viewState: LiveData<UsersViewState>
        get() = _viewState

    private val _shouldDisplayProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    val shouldDisplayProgressBar: LiveData<Boolean>
        get() = _shouldDisplayProgressBar

    fun onTriggerEvent(event: UsersStateEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is UsersStateEvent.GetUsersEvent -> {
                        getUsers()
                    }

                    is UsersStateEvent.None -> {

                    }
                }
            } catch (e: Exception) {
                Log.e("wojtas", "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d("wojtas", "launchJob: finally called.")
            }
        }
    }

    private fun getUsers() {

        userUseCases.getUsersUseCase.getUsers()
            .onEach { dataState ->
                _shouldDisplayProgressBar.value = dataState.loading

                dataState.data?.let { viewState ->
                    _viewState.value = viewState
                }

                dataState.error?.let { error ->
                    Log.e("wojtas", "newSearch: ${error}")
                    // dialogQueue.appendErrorMessage("An Error Occurred", error)
                }
            }.launchIn(viewModelScope)
    }

}
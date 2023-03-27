package com.example.userwithhilt_retrofit.ui.users.noteList

import com.example.userwithhilt_retrofit.domain.model.UserItem

data class UserListViewState(
    val isLoading: Boolean = false,
    val notesData: List<UserItem> = emptyList(),
    val message: String = ""
    //val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    //val isOrderSectionVisible: Boolean = false
)
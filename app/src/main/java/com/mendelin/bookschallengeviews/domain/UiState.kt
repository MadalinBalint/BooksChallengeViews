package com.mendelin.bookschallengeviews.domain

data class UiState(
    val isLoading: Boolean = false,
    val booksList: List<Book> = listOf(),
    val errorMessage: String = "",
)

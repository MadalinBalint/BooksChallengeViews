package com.mendelin.bookschallengeviews.books_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendelin.bookschallengeviews.data.model.BookModel
import com.mendelin.bookschallengeviews.data.model.toBook
import com.mendelin.bookschallengeviews.domain.GetBooksUseCase
import com.mendelin.bookschallengeviews.domain.Resource
import com.mendelin.bookschallengeviews.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewmodel @Inject constructor(private val useCase: GetBooksUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchBooksList()
    }

    private fun fetchBooksList() {
        viewModelScope.launch {
            useCase().flowOn(Dispatchers.IO)
                .collect { response ->
                    when (response) {
                        is Resource.Loading -> {
                            _uiState.update {
                                UiState(isLoading = true)
                            }
                        }

                        is Resource.Success -> {
                            val data = response.data!!
                            _uiState.update {
                                UiState(booksList = data.map(BookModel::toBook))
                            }
                        }

                        is Resource.Error -> {
                            _uiState.update {
                                UiState(errorMessage = response.errorMessage!!)
                            }
                        }
                    }

                }
        }
    }
}


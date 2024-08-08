package com.mendelin.bookschallengeviews.data.repository

import com.mendelin.bookschallengeviews.data.remote.BooksApi
import javax.inject.Inject


class BooksRepository @Inject constructor(val api: BooksApi) {
    suspend fun getBooks() =
        api.getBooks()
}

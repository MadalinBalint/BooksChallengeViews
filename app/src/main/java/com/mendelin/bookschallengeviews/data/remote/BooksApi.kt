package com.mendelin.bookschallengeviews.data.remote

import com.mendelin.bookschallengeviews.data.model.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface BooksApi {
    @GET("raw/51eZ6Vcr")
    suspend fun getBooks(): Response<BooksResponse>
}

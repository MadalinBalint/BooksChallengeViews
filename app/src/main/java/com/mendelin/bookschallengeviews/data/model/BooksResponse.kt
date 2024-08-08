package com.mendelin.bookschallengeviews.data.model

import androidx.annotation.Keep
import com.mendelin.bookschallengeviews.data.model.BookModel

@Keep
data class BooksResponse(
    val books: List<BookModel>
)

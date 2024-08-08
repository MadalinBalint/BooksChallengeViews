package com.mendelin.bookschallengeviews.data.model

import androidx.annotation.Keep
import com.mendelin.bookschallengeviews.domain.Book

@Keep
data class BookModel(
    val title: String,
    val author: String,
    val isbns: List<String>,
    val wiki: String,
    val image: UrlModel
)

fun BookModel.toBook() =
    Book(
        title = this.title,
        author = this.author,
        wiki = this.wiki,
        url = this.image.url
    )

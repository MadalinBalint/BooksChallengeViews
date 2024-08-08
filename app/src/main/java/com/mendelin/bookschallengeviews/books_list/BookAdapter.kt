package com.mendelin.booksinfochallenge.ui.books_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.bookschallengeviews.BookItemBinding
import com.mendelin.bookschallengeviews.domain.Book


class BookAdapter : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallBack()) {

    private val booksList: ArrayList<Book> = arrayListOf()

    inner class BookViewHolder(var binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBook(book: Book) {
            binding.book = book
            binding.executePendingBindings()
        }
    }

    class BookDiffCallBack : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = booksList[position]
        holder.bindBook(book)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    fun setList(list: List<Book>) {
        booksList.apply {
            clear()
            addAll(list)
        }

        submitList(list)
        notifyItemRangeChanged(0, list.size)
    }
}

package com.mendelin.bookschallengeviews.books_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mendelin.bookschallengeviews.databinding.FragmentFirstBinding
import com.mendelin.booksinfochallenge.ui.books_list.BookAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksListFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null
    private val viewModel: BooksViewModel by viewModels()
    lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeViewModel()
    }

    fun setupUi() {
        bookAdapter = BookAdapter()

        binding?.booksRecyclerView?.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            itemAnimator = null
            isNestedScrollingEnabled = true
        }

        viewModel.fetchBooksList()
    }

    fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding?.progressBar?.visibility =
                        if (state.isLoading) View.VISIBLE else View.INVISIBLE

                    bookAdapter.setList(state.booksList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

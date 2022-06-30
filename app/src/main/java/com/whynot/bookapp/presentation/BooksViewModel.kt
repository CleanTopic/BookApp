package com.whynot.bookapp.presentation

import androidx.lifecycle.*
import com.whynot.bookapp.entities.BookWithStatus
import com.whynot.bookapp.mappers.BookWithStatusMapper
import com.whynot.domain.Entities.Volume
import com.whynot.domain.UseCases.BookMarkBookUseCase
import com.whynot.domain.UseCases.GetBookMarksUseCase
import com.whynot.domain.UseCases.GetBooksUseCase
import com.whynot.domain.UseCases.UnBookMarkBookUseCase
import com.whynot.domain.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BooksViewModel(
    private val getBooksUseCase: GetBooksUseCase,
    private val getBookmarksUseCase: GetBookMarksUseCase,
    private val bookmarkBookUseCase: BookMarkBookUseCase,
    private val unbookmarkBookUseCase: UnBookMarkBookUseCase,
    private val mapper: BookWithStatusMapper
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _books = MutableLiveData<List<BookWithStatus>>()
    val books = _books

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteBooks = arrayListOf<Volume>()

    fun getBooks(author: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val booksResult = getBooksUseCase.invoke(author)) {
                is Result.Success -> {
                    _remoteBooks.clear()
                    _remoteBooks.addAll(booksResult.data)

                    val bookMarksFlow = getBookmarksUseCase.invoke()
                    bookMarksFlow.collect { bookmarks ->
                        books.value = mapper.fromVolumeToBookWithStatus(_remoteBooks, bookmarks)
                        _dataLoading.postValue(false)
                    }
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    books.value = emptyList()
                    _error.postValue(booksResult.exception.message)
                }
            }
        }
    }

    fun bookmark(book: BookWithStatus) {
        viewModelScope.launch {
            bookmarkBookUseCase.invoke(mapper.fromBookWithStatusToVolume(book))
        }
    }

    fun unbookmark(book: BookWithStatus) {
        viewModelScope.launch {
            unbookmarkBookUseCase.invoke(mapper.fromBookWithStatusToVolume(book))
        }
    }

    class BooksViewModelFactory(
        private val getBooksUseCase: GetBooksUseCase,
        private val getBookmarksUseCase: GetBookMarksUseCase,
        private val bookmarkBookUseCase: BookMarkBookUseCase,
        private val unbookmarkBookUseCase: UnBookMarkBookUseCase,
        private val mapper: BookWithStatusMapper
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BooksViewModel(
                getBooksUseCase,
                getBookmarksUseCase,
                bookmarkBookUseCase,
                unbookmarkBookUseCase,
                mapper
            ) as T
        }
    }
}





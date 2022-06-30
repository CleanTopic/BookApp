package com.whynot.domain.UseCases

import com.whynot.domain.Repositories.BooksRepository

class GetBookMarksUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke()= booksRepository.getBookmarks()
}
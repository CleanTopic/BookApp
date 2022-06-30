package com.whynot.domain.UseCases

import com.whynot.domain.Repositories.BooksRepository

class GetBooksUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(author: String)= booksRepository.getRemoteBooks(author)
}
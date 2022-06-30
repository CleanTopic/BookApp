package com.whynot.domain.UseCases

import com.whynot.domain.Entities.Volume
import com.whynot.domain.Repositories.BooksRepository

class UnBookMarkBookUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(book: Volume) = booksRepository.unBookMark(book)
}
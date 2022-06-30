package com.whynot.domain.repositories.books

import com.whynot.domain.Entities.Volume

interface BooksRemoteDataSource {
    suspend fun getBooks(author: String): Result<List<Volume>>
}
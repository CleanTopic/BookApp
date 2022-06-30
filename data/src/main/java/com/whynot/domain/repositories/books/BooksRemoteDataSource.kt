package com.whynot.domain.repositories.books

import com.whynot.domain.Entities.Volume
import com.whynot.domain.common.Result

interface BooksRemoteDataSource {
    suspend fun getBooks(author: String): Result<List<Volume>>
}
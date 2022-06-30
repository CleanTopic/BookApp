package com.whynot.domain.repositories.books

import com.whynot.domain.Entities.Volume
import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    suspend fun bookMark(book: Volume)
    suspend fun unBookMark(book: Volume)
    suspend fun getBookMark(): Flow<List<Volume>>
}
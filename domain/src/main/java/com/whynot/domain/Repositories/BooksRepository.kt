package com.whynot.domain.Repositories

import com.whynot.domain.Entities.Volume
import com.whynot.domain.common.Result
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getRemoteBooks(author: String): Result<List<Volume>>

    suspend fun getBookmarks(): Flow<List<Volume>>

    suspend fun bookmark(book: Volume)

    suspend fun unBookMark(book: Volume)
}
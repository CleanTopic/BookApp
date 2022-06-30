package com.whynot.domain.repositories.books;

import com.whynot.domain.Entities.Volume
import com.whynot.domain.Repositories.BooksRepository
import com.whynot.domain.repositories.books.BooksLocalDataSource
import com.whynot.domain.repositories.books.BooksRemoteDataSource
import kotlinx.coroutines.flow.Flow
import com.whynot.domain.common.Result


class BooksRepositoryImpl(
    private val localDataSource:BooksLocalDataSource,
    private val remoteDataSource:BooksRemoteDataSource
) : BooksRepository {

    override suspend fun getRemoteBooks(author: String): Result<List<Volume>> {
        return remoteDataSource.getBooks(author)
    }

    override suspend fun getBookmarks(): Flow<List<Volume>> {
        return localDataSource.getBookMark()
    }

    override suspend fun bookmark(book: Volume) {
        localDataSource.bookMark(book)
    }

    override suspend fun unBookMark(book: Volume) {
        localDataSource.unBookMark(book)
    }


}
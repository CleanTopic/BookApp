package com.whynot.domain.repositories.books

import androidx.room.Dao
import com.whynot.domain.Entities.Volume
import com.whynot.domain.db.BookDao
import com.whynot.domain.entities.BookEntity
import com.whynot.domain.mappers.BookEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BooksLocalDataSourceImpl(private val booksDao: BookDao,
private val dispatcher: CoroutineDispatcher,
private val bookEntityMapper: BookEntityMapper):BooksLocalDataSource {
    override suspend fun bookMark(book: Volume) {
        booksDao.saveBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun unBookMark(book: Volume) {
        booksDao.deleteBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun getBookMark(): Flow<List<Volume>> {
        val savedBooksFlow = booksDao.getSavedBooks()
        return savedBooksFlow.map { list ->
            list.map {element ->
                bookEntityMapper.toVolume(element)
            }
        }
    }
}
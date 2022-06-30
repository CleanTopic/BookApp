package com.whynot.bookapp.di

import android.content.Context
import com.whynot.bookapp.BuildConfig
import com.whynot.domain.api.NetworkModule
import com.whynot.domain.db.BooksDataBase
import com.whynot.domain.mappers.BookApiResponseMapper
import com.whynot.domain.mappers.BookEntityMapper
import com.whynot.domain.repositories.books.BooksLocalDataSource
import com.whynot.domain.repositories.books.BooksLocalDataSourceImpl
import com.whynot.domain.repositories.books.BooksRemoteDataSourceImpl
import com.whynot.domain.repositories.books.BooksRepositoryImpl
import kotlinx.coroutines.Dispatchers

object ServiceLocator {
    private var database: BooksDataBase? = null
    private val networkModule by lazy {
        NetworkModule()
    }
    private val bookEntityMapper by lazy {
        BookEntityMapper()
    }

    @Volatile
    var booksRepository: BooksRepositoryImpl? = null

    fun provideBooksRepository(context: Context): BooksRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return booksRepository ?: createBooksRepository(context)
        }
    }

    private fun createBooksRepository(context: Context): BooksRepositoryImpl {
        val newRepo =
            BooksRepositoryImpl(
                createBooksLocalDataSource(context),
                BooksRemoteDataSourceImpl(
                    networkModule.createBooksApi(BuildConfig.GOOGLE_APIS_ENDPOINT),
                    BookApiResponseMapper()
                )
            )
        booksRepository = newRepo
        return newRepo
    }

    private fun createBooksLocalDataSource(context: Context): BooksLocalDataSource {
        val database = database ?: createDataBase(context)
        return BooksLocalDataSourceImpl(
            database.bookDao(),
            Dispatchers.IO,
            bookEntityMapper
        )
    }

    private fun createDataBase(context: Context): BooksDataBase {
        val result = BooksDataBase.getDataBase(context)
        database = result
        return result
    }
}
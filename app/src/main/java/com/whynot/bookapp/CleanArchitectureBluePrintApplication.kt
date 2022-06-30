package com.whynot.bookapp

import android.app.Application
import android.os.Bundle
import com.whynot.bookapp.di.ServiceLocator
import com.whynot.bookapp.mappers.BookWithStatusMapper
import com.whynot.domain.UseCases.BookMarkBookUseCase
import com.whynot.domain.UseCases.GetBookMarksUseCase
import com.whynot.domain.UseCases.GetBooksUseCase
import com.whynot.domain.UseCases.UnBookMarkBookUseCase
import com.whynot.domain.repositories.books.BooksRepositoryImpl
import timber.log.Timber


class CleanArchitectureBluePrintApplication: Application() {
   private val booksRepository: BooksRepositoryImpl
    get() = ServiceLocator.provideBooksRepository(this)


    val getBookUseCase: GetBooksUseCase
    get() = GetBooksUseCase(booksRepository)

    val getBookMarksUseCase: GetBookMarksUseCase
    get() = GetBookMarksUseCase(booksRepository)

    val bookMarkBooksUseCase: BookMarkBookUseCase
    get() = BookMarkBookUseCase(booksRepository)

    val unBookMarkBookUseCase: UnBookMarkBookUseCase
    get() = UnBookMarkBookUseCase(booksRepository)

    val bookWithStatusMapper = BookWithStatusMapper()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
package com.whynot.bookapp

import android.app.Application
import android.os.Bundle
import timber.log.Timber

class CleanArchitectureBluePrintApplication: Application() {
    private val bookRepository: BooksRepositoryImpl
    get() = ServiceLocater.probideBookRepository(this)


    val getBookUseCase: GetBookUseCase
    get() = GetBookUseCase(bookRepository)

    val getBookMarksUseCase: GetBookMarksUseCase
    get() = GetBookMarksUseCase(bookRepository)

    val bookMarkBooksUseCase: BookMarkBookUseCase
    get() = BookMarkBookUseCase(bookRepository)

    val unBookMarkBookUseCase: UnBookMarkBookUseCase
    get() = UnBookMarkBookUseCase(bookRepository)

    val bookWithStatusMapper: BookWithStatusMapper()

    override fun onCreate(savedInsanceState: Bundle) {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
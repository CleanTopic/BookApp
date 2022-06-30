package com.whynot.domain.db

import androidx.annotation.NonNull
import androidx.room.*
import com.whynot.domain.entities.BookEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBook(book: BookEntity)

    @Query("SELECT * FROM book")
    fun getSavedBooks(): Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(book: BookEntity)
}
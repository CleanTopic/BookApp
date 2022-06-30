package com.whynot.domain.db

import android.content.Context
import androidx.room.*
import com.whynot.domain.entities.BookEntity


@Database(entities = [BookEntity::class], version = 1, exportSchema = false )
@TypeConverters(Converters::class)
abstract class BooksDataBase: RoomDatabase(){
    abstract fun bookDao():BookDao

    companion object{
        @Volatile
        private var INSTANCE: BooksDataBase? = null
        fun getDataBase(appContext: Context):BooksDataBase{
            val tempInstance =
                INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                appContext, BooksDataBase::class.java,
                    BooksDataBase::class.simpleName!!
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
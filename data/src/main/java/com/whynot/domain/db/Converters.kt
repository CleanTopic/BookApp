package com.whynot.domain.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromAuthorList(value: List<String>): String{
        return value.joinToString { "," }

    }

    @TypeConverter
    fun toAuthorList(value:String): List<String>{
        return value.split(",")
    }
}
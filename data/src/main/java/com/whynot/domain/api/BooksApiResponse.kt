package com.whynot.domain.api

import com.squareup.moshi.Json


class BooksApiResponse(val items: List<Item>)

data class Item(
    @field:Json(name = "id")
    val id : String,
    @field:Json(name = "volumeInfo")
    val volumeInfo: ApiVolumeInfo
)

data class ApiVolumeInfo(
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks?
)

data class ImageLinks(val smallThumbnail: String, val thumbnail: String)
/*
import android.content.ClipData
import android.provider.MediaStore
import com.squareup.moshi.Json
import retrofit2.http.Field

class BooksApiResponse(val items:List<Item>)

data class Item{
    @field: Json(name ="id")
    val id:String,
    @field: Json(name ="volumeInfo")
    val volumeInfo: ApiVolumeInfo

}

data class ApiVolumeInfo{
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks?
}

data class ImageLinks(val smallThubnail: String, val thumbnails: String)
*/


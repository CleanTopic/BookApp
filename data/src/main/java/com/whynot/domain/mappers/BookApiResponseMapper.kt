package com.whynot.domain.mappers

import com.whynot.domain.Entities.Volume
import com.whynot.domain.Entities.VolumeInfo
import com.whynot.domain.api.BooksApiResponse

class BookApiResponseMapper {
    fun toVolumeList(response: BooksApiResponse): List<Volume>{
        return response.items.map{
            Volume(
                it.id, VolumeInfo(
                    it.volumeInfo.title,
                    it.volumeInfo.authors,
                    it.volumeInfo.imageLinks?.thumbnail?.replace("http","https")
                )
            )
        }
    }
}
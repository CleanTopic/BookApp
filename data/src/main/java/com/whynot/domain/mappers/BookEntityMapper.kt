package com.whynot.domain.mappers

import com.whynot.domain.Entities.Volume
import com.whynot.domain.Entities.VolumeInfo
import com.whynot.domain.entities.BookEntity

class BookEntityMapper {
    fun toBookEntity(volume: Volume): BookEntity{
        return BookEntity(
            id = volume.id,
            title = volume.volumeInfo.title,
            authors = volume.volumeInfo.authors,
            imageUrl = volume.volumeInfo.imageUrl
        )
    }

    fun toVolume(bookEntity: BookEntity):Volume{
        return Volume(
            bookEntity.id,
            VolumeInfo(bookEntity.title, bookEntity.authors, bookEntity.imageUrl)
        )
    }
}
package com.example.spotify.data.converter

import com.example.spotify.data.db.ArtistEntity
import com.example.spotify.models.data.net.ArtistResponse

/**
 * Конвертер сетевой модели [ArtistResponse] в модель базы данных [ArtistEntity]
 */
class ArtistResponseToEntityConverter {

    /**
     * Конвертирует [ArtistResponse] в [ArtistEntity]
     *
     * @param from данные для конвертации
     */
    fun convert(from: ArtistResponse): ArtistEntity =
        ArtistEntity(
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            genres = from.genres.joinToString { it },
            smallImage = from.images.lastOrNull()?.url.orEmpty(),
            bigImage = from.images.firstOrNull()?.url.orEmpty()
        )
}
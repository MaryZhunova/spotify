package com.example.spotify.data.converter

import com.example.spotify.models.data.AlbumInfo
import com.example.spotify.models.data.TrackInfo
import com.example.spotify.models.data.net.Album
import com.example.spotify.models.data.net.TrackResponse

/**
 * Конвертер сетевой модели [TrackResponse] в дата модель [TrackInfo]
 */
class TrackResponseToInfoConverter {

    /**
     * Конвертирует [TrackResponse] в [TrackInfo]
     *
     * @param from данные для конвертации
     */
    fun convert(from: TrackResponse): TrackInfo =
        TrackInfo(
            id = from.id,
            name = from.name,
            previewUrl = from.previewUrl,
            duration = from.duration,
            artists = from.artists.joinToString { it.name },
            album = convertAlbum(from.album),
            isExplicit = from.isExplicit,
            isPlayable = from.isPlayable,
            popularity = from.popularity
        )

    private fun convertAlbum(from: Album): AlbumInfo =
        AlbumInfo(
            id = from.id,
            name = from.name,
            image = from.images.last().url
        )
}

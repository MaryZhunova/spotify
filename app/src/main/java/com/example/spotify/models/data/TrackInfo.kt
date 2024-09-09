package com.example.spotify.models.data

/**
 * Информация о треке
 *
 * @property id уникальный идентификатор трека
 * @property name название трека
 * @property artists список исполнителей, исполняющих трек (в виде строки, разделенные запятой)
 * @property album альбом, в который входит трек
 * @property popularity популярность трека
 */
data class TrackInfo(
    val id: String,
    val name: String,
    val previewUrl: String?,
    val duration: Int,
    val artists: String,
    val album: AlbumInfo,
    val isExplicit: Boolean,
    val isPlayable: Boolean,
    val popularity: Int
)

/**
 * Информация об альбоме
 *
 * @property id уникальный идентификатор альбома
 * @property name название альбома
 * @property image URL изображения альбома
 */
data class AlbumInfo(
    val id: String,
    val name: String,
    val image: String
)
package com.example.spotify.data.models.network

import com.google.gson.annotations.SerializedName

/**
 * Ответ на запрос самых популярных треков исполнителя
 *
 * @property tracks список треков, каждый из которых представлен объектом `TrackResponse`
 */
data class ArtistsTopTracksResponse(
    @SerializedName("tracks") val tracks: List<TrackResponse>
)
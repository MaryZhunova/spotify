package com.example.spotify.domain

import com.example.spotify.models.data.ArtistInfo
import com.example.spotify.models.data.TopTrackInfo
import com.example.spotify.models.data.TrackInfo

/**
 * Интерфейс репозитория для получения информации о треках и исполнителях из Spotify
 */
interface SpotifyInfoRepository {

    /**
     * Получает информацию о топе треков исполнителя
     *
     * @param id идентификатор исполнителя
     *
     * @return информация о топе треков исполнителя в виде списка объектов [TrackInfo]
     */
    suspend fun getArtistsTopTracks(id: String): List<TopTrackInfo>

    /**
     * Получает информацию об исполнителе из бд
     *
     * @param id идентификатор исполнителя
     *
     * @return информация о топе треков исполнителя в виде объекта [ArtistInfo]
     */
    suspend fun getArtistsInfo(id: String): ArtistInfo

    /**
     * Очищает бд
     */
    suspend fun clear()
}
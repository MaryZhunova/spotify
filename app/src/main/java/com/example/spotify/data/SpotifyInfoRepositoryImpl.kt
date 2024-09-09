package com.example.spotify.data

import com.example.spotify.data.converter.ArtistEntityToInfoConverter
import com.example.spotify.data.converter.TrackEntityToInfoConverter
import com.example.spotify.data.db.ArtistDao
import com.example.spotify.data.db.TrackDao
import com.example.spotify.data.net.SpotifyInfoApiMapper
import com.example.spotify.domain.SpotifyInfoRepository
import com.example.spotify.domain.security.AuthRepository
import com.example.spotify.models.data.AlbumInfo
import com.example.spotify.models.data.ArtistInfo
import com.example.spotify.models.data.TopTrackInfo
import com.example.spotify.models.data.TrackInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Реализация репозитория для получения информации о треках и исполнителях из Spotify
 *
 * @constructor
 * @param apiMapper маппер для преобразования данных из API в модели
 * @param authRepository репозиторий для работы с токенами доступа
 * @param trackConverter конвертер для преобразования ответа API о треке в модель [TrackInfo]
 * @param artistConverter конвертер для преобразования модели бд об исполнителе в модель [ArtistInfo]
 * @param artistDao
 */
class SpotifyInfoRepositoryImpl @Inject constructor(
    private val apiMapper: SpotifyInfoApiMapper,
    private val authRepository: AuthRepository,
    private val trackConverter: TrackEntityToInfoConverter,
    private val artistConverter: ArtistEntityToInfoConverter,
    private val artistDao: ArtistDao,
    private val trackDao: TrackDao
) : SpotifyInfoRepository {

    override suspend fun getArtistsTopTracks(id: String): List<TopTrackInfo> =
        withContext(Dispatchers.IO) {
            val token = authRepository.getAccessToken()
            apiMapper.getArtistsTopTracks(token, id).tracks.map { from ->
                TopTrackInfo(
                    id = from.id,
                    name = from.name,
                    previewUrl = from.previewUrl,
                    duration = from.duration,
                    artists = from.artists.joinToString { it.name },
                    album = AlbumInfo(
                        id = from.album.id,
                        name = from.album.name,
                        image = from.album.images.last().url
                    ),
                    isExplicit = from.isExplicit,
                    isPlayable = from.isPlayable,
                    popularity = from.popularity,
                    isFavorite = trackDao.isTrackInDatabase(from.id)
                )
            }
        }

    override suspend fun getArtistsInfo(id: String): ArtistInfo = withContext(Dispatchers.IO) {
        artistConverter.convert(artistDao.getById(id))
    }

    override suspend fun clear() = withContext(Dispatchers.IO) {
        artistDao.deleteAll()
        trackDao.deleteAll()
    }
}
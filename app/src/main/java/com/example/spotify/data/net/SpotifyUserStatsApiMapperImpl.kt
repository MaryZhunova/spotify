package com.example.spotify.data.net

import com.example.spotify.models.data.net.TopArtistsResponse
import com.example.spotify.models.data.net.TopTracksResponse
import com.example.spotify.models.data.net.UserProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Реализация интерфейса [SpotifyUserStatsApiMapper],
 * которая использует [SpotifyUserStatsApiService] для выполнения запросов к API Spotify
 *
 * @constructor
 * @param apiService Сервис для выполнения запросов к API Spotify
 */
class SpotifyUserStatsApiMapperImpl @Inject constructor(
    private val apiService: SpotifyUserStatsApiService
) : SpotifyUserStatsApiMapper {

    override suspend fun getCurrentUserProfile(accessToken: String): UserProfileResponse =
        withContext(Dispatchers.IO) {
            val response = apiService.getCurrentUserProfile("Bearer $accessToken").execute()
            return@withContext if (response.isSuccessful) {
                checkNotNull(response.body())
            } else {
                throw Exception()
            }
        }

    override suspend fun getTopTracks(
        accessToken: String,
        timeRange: String,
        limit: Int
    ): TopTracksResponse = withContext(Dispatchers.IO) {
        val response = apiService.getTopTracks(
            token = "Bearer $accessToken",
            timeRange = timeRange,
            limit = limit
        ).execute()
        return@withContext if (response.isSuccessful) {
            checkNotNull(response.body())
        } else {
            throw Exception()
        }
    }

    override suspend fun getTopTracksNextPage(
        accessToken: String,
        url: String
    ): TopTracksResponse =
        withContext(Dispatchers.IO) {
            val response = apiService.getTopTracksNextPage(
                token = "Bearer $accessToken",
                url = url
            ).execute()
            return@withContext if (response.isSuccessful) {
                checkNotNull(response.body())
            } else {
                throw Exception()
            }
        }

    override suspend fun getTopArtists(
        accessToken: String,
        timeRange: String,
        limit: Int
    ): TopArtistsResponse = withContext(Dispatchers.IO) {
        val response = apiService.getTopArtists(
            token = "Bearer $accessToken",
            timeRange = timeRange,
            limit = limit
        ).execute()
        return@withContext if (response.isSuccessful) {
            checkNotNull(response.body())
        } else {
            throw Exception()
        }
    }

    override suspend fun getTopArtistsNextPage(
        accessToken: String,
        url: String
    ): TopArtistsResponse = withContext(Dispatchers.IO) {
        val response = apiService.getTopArtistsNextPage(
            token = "Bearer $accessToken",
            url = url
        ).execute()
        return@withContext if (response.isSuccessful) {
            checkNotNull(response.body())
        } else {
            throw Exception()
        }
    }
}
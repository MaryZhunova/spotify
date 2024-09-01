package com.example.spotify.data.security.net

import com.example.spotify.models.data.net.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientCredentialsApiService {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun exchangeCodeForToken(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): Response<AccessTokenResponse>

    @FormUrlEncoded
    @POST("api/token")
    suspend fun refreshAuthToken(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") token: String,
    ): Response<AccessTokenResponse>
}
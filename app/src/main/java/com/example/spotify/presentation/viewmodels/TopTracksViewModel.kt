package com.example.spotify.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotify.domain.SpotifyStatsRepository
import com.example.spotify.models.data.TrackInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopTracksViewModel @Inject constructor(
    private val statsRepository: SpotifyStatsRepository
) : ViewModel() {

    private val _topTracks = mutableStateOf<List<TrackInfo>>(emptyList())
    val topTracks: State<List<TrackInfo>>
        get() = _topTracks

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean>
        get() = _isLoading

    private var nextPageUrl: String? = null

    fun fetchTopTracks() = viewModelScope.launch {
        _isLoading.value = true
        statsRepository.getTopTracks("short_term", 10) {
            _isLoading.value = false
            if (it != null) {
                _topTracks.value = it.items
                nextPageUrl = it.next
            }
        }
    }

    fun fetchNextPage() = viewModelScope.launch {
        nextPageUrl?.let {
            statsRepository.getNextPage(it) { info ->
                _topTracks.value = (_topTracks.value) + info?.items.orEmpty()
                nextPageUrl = info?.next
            }
        }
    }
}
package com.fern.releases

sealed interface ReleasesEvent {
    data object OnTryAgainClick : ReleasesEvent
}

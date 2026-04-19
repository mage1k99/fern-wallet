package com.fern.contributors

sealed interface ContributorsEvent {
    data object TryAgainButtonClicked : ContributorsEvent
}

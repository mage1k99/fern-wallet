package com.fern.features

sealed interface FeaturesUiEvent {
    data class ToggleFeature(val key: String) : FeaturesUiEvent
}

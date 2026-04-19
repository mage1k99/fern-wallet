package com.fern.piechart

import androidx.compose.runtime.Immutable
import com.fern.data.model.Category

@Immutable
data class SelectedCategory(
    val category: Category // null - Unspecified
)

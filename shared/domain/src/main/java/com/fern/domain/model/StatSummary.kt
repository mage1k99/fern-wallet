package com.fern.domain.model

import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.NonNegativeInt
import com.fern.data.model.primitive.PositiveDouble

data class StatSummary(
    val trnCount: NonNegativeInt,
    val values: Map<AssetCode, PositiveDouble>,
) {
    companion object {
        val Zero = StatSummary(
            values = emptyMap(),
            trnCount = NonNegativeInt.Zero
        )
    }
}
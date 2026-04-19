package com.fern.legacy.data.model

import androidx.compose.runtime.Immutable
import com.fern.base.time.TimeProvider
import com.fern.data.model.IntervalType
import com.fern.legacy.forDisplay
import com.fern.legacy.incrementDate
import java.time.Instant

@Suppress("DataClassFunctions")
@Immutable
data class LastNTimeRange(
    val periodN: Int,
    val periodType: IntervalType,
) {
    fun fromDate(
        timeProvider: TimeProvider
    ): Instant = periodType.incrementDate(
        date = timeProvider.utcNow(),
        intervalN = -periodN.toLong()
    )

    fun forDisplay(): String =
        "$periodN ${periodType.forDisplay(periodN)}"
}

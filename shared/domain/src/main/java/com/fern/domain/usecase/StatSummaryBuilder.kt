package com.fern.domain.usecase

import com.fern.data.model.PositiveValue
import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.NonNegativeInt
import com.fern.data.model.primitive.PositiveDouble
import com.fern.domain.model.StatSummary

class StatSummaryBuilder {
    private var count = 0
    private val values = mutableMapOf<AssetCode, PositiveDouble>()

    fun process(value: PositiveValue) {
        count++
        val asset = value.asset
        // Because it might overflow
        PositiveDouble.from(
            (values[asset]?.value ?: 0.0) + value.amount.value
        ).onRight { newValue ->
            values[asset] = newValue
        }
    }

    fun build(): StatSummary = StatSummary(
        trnCount = NonNegativeInt.unsafe(count),
        values = values,
    )
}
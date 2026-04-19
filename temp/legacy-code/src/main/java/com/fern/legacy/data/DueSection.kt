package com.fern.legacy.data

import androidx.compose.runtime.Immutable
import com.fern.base.legacy.Transaction
import com.fern.wallet.domain.pure.data.IncomeExpensePair
import kotlinx.collections.immutable.ImmutableList

@Deprecated("Uses legacy Transaction")
@Immutable
data class LegacyDueSection(
    val trns: ImmutableList<Transaction>,
    val expanded: Boolean,
    val stats: IncomeExpensePair
)

@Deprecated("Legacy data model")
@Immutable
data class DueSection(
    val trns: ImmutableList<com.fern.data.model.Transaction>,
    val expanded: Boolean,
    val stats: IncomeExpensePair
)
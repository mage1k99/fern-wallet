package com.fern.wallet.domain.data

import androidx.compose.runtime.Immutable
import com.fern.base.legacy.TransactionHistoryItem
import java.time.LocalDate

@Immutable
data class TransactionHistoryDateDivider(
    val date: LocalDate,
    val income: Double,
    val expenses: Double
) : TransactionHistoryItem

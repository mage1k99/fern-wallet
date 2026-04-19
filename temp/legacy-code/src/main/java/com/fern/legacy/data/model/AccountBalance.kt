package com.fern.legacy.data.model

import androidx.compose.runtime.Immutable
import com.fern.legacy.datamodel.Account

@Immutable
data class AccountBalance(
    val account: Account,
    val balance: Double
)

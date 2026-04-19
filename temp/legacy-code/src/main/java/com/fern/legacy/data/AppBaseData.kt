package com.fern.legacy.data

import androidx.compose.runtime.Immutable
import com.fern.data.model.Category
import com.fern.legacy.datamodel.Account
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class AppBaseData(
    val baseCurrency: String,
    val accounts: ImmutableList<Account>,
    val categories: ImmutableList<Category>
)

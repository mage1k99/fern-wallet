package com.fern.onboarding

import androidx.compose.runtime.Immutable
import com.fern.data.model.Category
import com.fern.legacy.data.model.AccountBalance
import com.fern.wallet.domain.data.IvyCurrency
import com.fern.wallet.domain.deprecated.logic.model.CreateAccountData
import com.fern.wallet.domain.deprecated.logic.model.CreateCategoryData
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class OnboardingDetailState(
    val currency: IvyCurrency,
    val accounts: ImmutableList<AccountBalance>,
    val accountSuggestions: ImmutableList<CreateAccountData>,
    val categories: ImmutableList<Category>,
    val categorySuggestions: ImmutableList<CreateCategoryData>
)

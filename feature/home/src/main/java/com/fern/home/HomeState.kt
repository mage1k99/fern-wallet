package com.fern.home

import androidx.compose.runtime.Immutable
import com.fern.base.legacy.Theme
import com.fern.base.legacy.TransactionHistoryItem
import com.fern.home.customerjourney.CustomerJourneyCardModel
import com.fern.legacy.data.AppBaseData
import com.fern.legacy.data.BufferInfo
import com.fern.legacy.data.LegacyDueSection
import com.fern.legacy.data.model.TimePeriod
import com.fern.wallet.domain.pure.data.IncomeExpensePair
import kotlinx.collections.immutable.ImmutableList
import java.math.BigDecimal

@Immutable
data class HomeState(
    val theme: Theme,
    val name: String,

    val period: TimePeriod,
    val baseData: AppBaseData,

    val history: ImmutableList<TransactionHistoryItem>,
    val stats: IncomeExpensePair,

    val balance: BigDecimal,

    val buffer: BufferInfo,

    val upcoming: LegacyDueSection,
    val overdue: LegacyDueSection,

    val customerJourneyCards: ImmutableList<CustomerJourneyCardModel>,
    val hideBalance: Boolean,
    val hideIncome: Boolean,
    val expanded: Boolean,
    val shouldShowAccountSpecificColorInTransactions: Boolean
)

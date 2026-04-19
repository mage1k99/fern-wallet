package com.fern.wallet.domain.action.transaction

import com.fern.base.legacy.Transaction
import com.fern.data.db.dao.read.TransactionDao
import com.fern.frp.action.FPAction
import com.fern.frp.action.thenFilter
import com.fern.legacy.datamodel.temp.toLegacyDomain
import java.util.UUID
import javax.inject.Inject

class TrnsWithRangeAndAccFiltersAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<TrnsWithRangeAndAccFiltersAct.Input, List<Transaction>>() {

    override suspend fun Input.compose(): suspend () -> List<Transaction> = suspend {
        transactionDao.findAllBetween(range.from(), range.to())
            .map { it.toLegacyDomain() }
    } thenFilter {
        accountIdFilterSet.contains(it.accountId) || accountIdFilterSet.contains(it.toAccountId)
    }

    data class Input(
        val range: com.fern.legacy.data.model.FromToTimeRange,
        val accountIdFilterSet: Set<UUID>
    )
}

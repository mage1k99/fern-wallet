package com.fern.wallet.domain.action.transaction

import com.fern.data.model.Transaction
import com.fern.data.repository.TransactionRepository
import com.fern.frp.action.FPAction
import com.fern.wallet.domain.pure.data.ClosedTimeRange
import javax.inject.Inject

class HistoryTrnsAct @Inject constructor(
    private val transactionRepository: TransactionRepository
) : FPAction<ClosedTimeRange, List<Transaction>>() {

    override suspend fun ClosedTimeRange.compose(): suspend () -> List<Transaction> = suspend {
        io {
            transactionRepository.findAllBetween(
                startDate = from,
                endDate = to
            )
        }
    }
}

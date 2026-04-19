package com.fern.wallet.domain.action.transaction

import com.fern.data.model.Transaction
import com.fern.data.repository.TransactionRepository
import com.fern.frp.action.FPAction
import javax.inject.Inject

class AllTrnsAct @Inject constructor(
    private val transactionRepository: TransactionRepository
) : FPAction<Unit, List<Transaction>>() {
    override suspend fun Unit.compose(): suspend () -> List<Transaction> = suspend {
        transactionRepository.findAll()
    }
}

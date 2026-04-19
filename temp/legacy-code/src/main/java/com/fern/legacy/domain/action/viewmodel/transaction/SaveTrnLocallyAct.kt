package com.fern.wallet.domain.action.viewmodel.transaction

import com.fern.base.legacy.Transaction
import com.fern.data.repository.TransactionRepository
import com.fern.frp.action.FPAction
import com.fern.frp.then
import com.fern.legacy.datamodel.toEntity
import javax.inject.Inject

class SaveTrnLocallyAct @Inject constructor(
    private val transactionRepo: TransactionRepository,
) : FPAction<Transaction, Unit>() {
    override suspend fun Transaction.compose(): suspend () -> Unit = {
        this.copy(
            isSynced = false
        ).toEntity()
    } then {
        transactionRepo::save then {}
    }
}

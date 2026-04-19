package com.fern.wallet.domain.action.transaction

import com.fern.base.legacy.Transaction
import com.fern.data.model.TransactionId
import com.fern.data.repository.TransactionRepository
import com.fern.data.repository.mapper.TransactionMapper
import com.fern.frp.action.FPAction
import com.fern.frp.then
import com.fern.legacy.datamodel.temp.toLegacy
import java.util.UUID
import javax.inject.Inject

class TrnByIdAct @Inject constructor(
    private val transactionRepo: TransactionRepository,
    private val mapper: TransactionMapper
) : FPAction<UUID, Transaction?>() {
    override suspend fun UUID.compose(): suspend () -> Transaction? = suspend {
        this // transactionId
    } then {
        transactionRepo.findById(TransactionId(it))
    } then {
        it?.toLegacy(mapper)
    }
}

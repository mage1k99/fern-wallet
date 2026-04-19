package com.fern.wallet.domain.action.viewmodel.home

import com.fern.data.db.dao.read.TransactionDao
import com.fern.frp.action.FPAction
import javax.inject.Inject

class HasTrnsAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<Unit, Boolean>() {
    override suspend fun Unit.compose(): suspend () -> Boolean = suspend {
        io {
            transactionDao.findAll_LIMIT_1().isNotEmpty()
        }
    }
}

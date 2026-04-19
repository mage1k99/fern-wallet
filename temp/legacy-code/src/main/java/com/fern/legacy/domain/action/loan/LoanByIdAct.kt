package com.fern.wallet.domain.action.loan

import com.fern.data.db.dao.read.LoanDao
import com.fern.frp.action.FPAction
import com.fern.legacy.datamodel.Loan
import com.fern.legacy.datamodel.temp.toLegacyDomain
import java.util.UUID
import javax.inject.Inject

class LoanByIdAct @Inject constructor(
    private val loanDao: LoanDao
) : FPAction<UUID, Loan?>() {
    override suspend fun UUID.compose(): suspend () -> Loan? = suspend {
        loanDao.findById(this)?.toLegacyDomain()
    }
}

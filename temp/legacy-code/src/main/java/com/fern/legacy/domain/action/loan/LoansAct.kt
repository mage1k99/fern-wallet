package com.fern.wallet.domain.action.loan

import com.fern.data.db.dao.read.LoanDao
import com.fern.frp.action.FPAction
import com.fern.frp.action.thenMap
import com.fern.frp.then
import com.fern.legacy.datamodel.Loan
import com.fern.legacy.datamodel.temp.toLegacyDomain
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class LoansAct @Inject constructor(
    private val loanDao: LoanDao
) : FPAction<Unit, ImmutableList<Loan>>() {
    override suspend fun Unit.compose(): suspend () -> ImmutableList<Loan> = suspend {
        loanDao.findAll()
    } thenMap { it.toLegacyDomain() } then { it.toImmutableList() }
}

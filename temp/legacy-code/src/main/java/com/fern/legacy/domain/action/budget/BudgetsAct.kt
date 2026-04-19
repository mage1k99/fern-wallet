package com.fern.wallet.domain.action.budget

import com.fern.data.db.dao.read.BudgetDao
import com.fern.frp.action.FPAction
import com.fern.frp.action.thenMap
import com.fern.frp.then
import com.fern.legacy.datamodel.Budget
import com.fern.legacy.datamodel.temp.toLegacyDomain
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class BudgetsAct @Inject constructor(
    private val budgetDao: BudgetDao
) : FPAction<Unit, ImmutableList<Budget>>() {
    override suspend fun Unit.compose(): suspend () -> ImmutableList<Budget> = suspend {
        budgetDao.findAll()
    } thenMap { it.toLegacyDomain() } then { it.toImmutableList() }
}

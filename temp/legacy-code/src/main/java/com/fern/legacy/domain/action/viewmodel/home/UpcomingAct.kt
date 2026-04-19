package com.fern.wallet.domain.action.viewmodel.home

import com.fern.data.model.Transaction
import com.fern.frp.action.FPAction
import com.fern.frp.then
import com.fern.wallet.domain.pure.data.ClosedTimeRange
import com.fern.wallet.domain.pure.data.IncomeExpensePair
import com.fern.wallet.domain.pure.transaction.isUpcoming
import javax.inject.Inject

class UpcomingAct @Inject constructor(
    private val dueTrnsInfoAct: DueTrnsInfoAct
) : FPAction<UpcomingAct.Input, UpcomingAct.Output>() {

    override suspend fun Input.compose(): suspend () -> Output = suspend {
        DueTrnsInfoAct.Input(
            range = range,
            baseCurrency = baseCurrency,
            dueFilter = ::isUpcoming
        )
    } then dueTrnsInfoAct then {
        Output(
            upcoming = it.dueIncomeExpense,
            upcomingTrns = it.dueTrns
        )
    }

    data class Input(
        val range: ClosedTimeRange,
        val baseCurrency: String
    )

    data class Output(
        val upcoming: IncomeExpensePair,
        val upcomingTrns: List<Transaction>
    )
}

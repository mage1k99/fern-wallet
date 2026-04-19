package com.fern.wallet.domain.action.viewmodel.home

import com.fern.frp.action.FPAction
import com.fern.frp.then
import com.fern.legacy.utils.ivyMinTime
import com.fern.wallet.domain.pure.data.ClosedTimeRange
import com.fern.wallet.domain.pure.data.IncomeExpensePair
import com.fern.wallet.domain.pure.transaction.isOverdue
import java.time.Instant
import javax.inject.Inject

class OverdueAct @Inject constructor(
    private val dueTrnsInfoAct: DueTrnsInfoAct
) : FPAction<OverdueAct.Input, OverdueAct.Output>() {

    override suspend fun Input.compose(): suspend () -> Output = suspend {
        DueTrnsInfoAct.Input(
            range = ClosedTimeRange(
                from = ivyMinTime(),
                to = toRange
            ),
            baseCurrency = baseCurrency,
            dueFilter = ::isOverdue
        )
    } then dueTrnsInfoAct then {
        Output(
            overdue = it.dueIncomeExpense,
            overdueTrns = it.dueTrns
        )
    }

    data class Input(
        val toRange: Instant,
        val baseCurrency: String
    )

    data class Output(
        val overdue: IncomeExpensePair,
        val overdueTrns: List<com.fern.data.model.Transaction>
    )
}

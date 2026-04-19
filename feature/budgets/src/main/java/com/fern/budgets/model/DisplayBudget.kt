package com.fern.budgets.model

import androidx.compose.runtime.Immutable
import com.fern.legacy.datamodel.Budget
import com.fern.wallet.domain.data.Reorderable

@Immutable
data class DisplayBudget(
    val budget: Budget,
    val spentAmount: Double
) : Reorderable {
    override fun getItemOrderNum(): Double {
        return budget.orderId
    }

    override fun withNewOrderNum(newOrderNum: Double): Reorderable {
        return this.copy(
            budget = budget.copy(
                orderId = newOrderNum
            )
        )
    }
}

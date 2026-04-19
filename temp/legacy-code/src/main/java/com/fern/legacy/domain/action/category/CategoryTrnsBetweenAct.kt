package com.fern.wallet.domain.action.category

import com.fern.base.legacy.Transaction
import com.fern.data.db.dao.read.TransactionDao
import com.fern.frp.action.FPAction
import com.fern.frp.action.thenMap
import com.fern.legacy.datamodel.temp.toLegacyDomain
import com.fern.wallet.domain.pure.data.ClosedTimeRange
import java.util.UUID
import javax.inject.Inject

class CategoryTrnsBetweenAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<CategoryTrnsBetweenAct.Input, List<Transaction>>() {

    override suspend fun Input.compose(): suspend () -> List<Transaction> = suspend {
        io {
            transactionDao.findAllByCategoryAndBetween(
                startDate = between.from,
                endDate = between.to,
                categoryId = categoryId
            )
        }
    } thenMap { it.toLegacyDomain() }

    data class Input(
        val categoryId: UUID,
        val between: ClosedTimeRange
    )
}

fun actInput(
    categoryId: UUID,
    between: ClosedTimeRange
) = CategoryTrnsBetweenAct.Input(
    categoryId = categoryId,
    between = between
)

package com.fern.domain.usecase.csv

import com.fern.base.model.TransactionType
import com.fern.data.model.AccountId
import com.fern.data.model.CategoryId
import com.fern.data.model.TransactionId
import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.NonNegativeDouble
import com.fern.data.model.primitive.NotBlankTrimmedString
import com.fern.data.model.primitive.PositiveDouble
import java.time.Instant

// TODO: Fix Ivy Explicit detekt false-positives
@SuppressWarnings("DataClassTypedIDs")
data class IvyCsvRow(
    val date: Instant?,
    val title: NotBlankTrimmedString?,
    val category: CategoryId?,
    val account: AccountId,
    val amount: NonNegativeDouble,
    val currency: AssetCode,
    val type: TransactionType,
    val transferAmount: PositiveDouble?,
    val transferCurrency: AssetCode?,
    val toAccountId: AccountId?,
    val receiveAmount: PositiveDouble?,
    val receiveCurrency: AssetCode?,
    val description: NotBlankTrimmedString?,
    val dueData: Instant?,
    val id: TransactionId
) {
    companion object {
        val Columns = listOf(
            "Date",
            "Title",
            "Category",
            "Account",
            "Amount",
            "Currency",
            "Type",
            "Transfer Amount",
            "Transfer Currency",
            "To Account",
            "Receive Amount",
            "Receive Currency",
            "Description",
            "Due Date",
            "ID",
        )
    }
}
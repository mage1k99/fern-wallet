package com.fern.planned.edit

import com.fern.base.model.TransactionType
import com.fern.data.model.Category
import com.fern.data.model.IntervalType
import com.fern.legacy.datamodel.Account
import com.fern.wallet.ui.theme.modal.RecurringRuleModalData
import com.fern.wallet.ui.theme.modal.edit.AccountModalData
import com.fern.wallet.ui.theme.modal.edit.CategoryModalData
import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDateTime
import javax.annotation.concurrent.Immutable

@Immutable
data class EditPlannedScreenState(
    val currency: String,
    val transactionType: TransactionType,
    val startDate: LocalDateTime?,
    val intervalN: Int?,
    val intervalType: IntervalType?,
    val oneTime: Boolean,
    val initialTitle: String?,
    val description: String?,
    val categories: ImmutableList<Category>,
    val accounts: ImmutableList<Account>,
    val account: Account?,
    val category: Category?,
    val amount: Double,
    val categoryModalVisible: Boolean,
    val descriptionModalVisible: Boolean,
    val deleteTransactionModalVisible: Boolean,
    val categoryModalData: CategoryModalData?,
    val accountModalData: AccountModalData?,
    val recurringRuleModalData: RecurringRuleModalData?,
    val transactionTypeModalVisible: Boolean,
    val amountModalVisible: Boolean
)

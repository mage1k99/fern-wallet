package com.fern.data.backup

import androidx.annotation.Keep
import com.fern.data.db.entity.AccountEntity
import com.fern.data.db.entity.BudgetEntity
import com.fern.data.db.entity.CategoryEntity
import com.fern.data.db.entity.LoanEntity
import com.fern.data.db.entity.LoanRecordEntity
import com.fern.data.db.entity.PlannedPaymentRuleEntity
import com.fern.data.db.entity.SettingsEntity
import com.fern.data.db.entity.TagAssociationEntity
import com.fern.data.db.entity.TagEntity
import com.fern.data.db.entity.TransactionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Suppress("DataClassDefaultValues")
data class IvyWalletCompleteData(
    @SerialName("accounts")
    val accounts: List<AccountEntity> = emptyList(),
    @SerialName("budgets")
    val budgets: List<BudgetEntity> = emptyList(),
    @SerialName("categories")
    val categories: List<CategoryEntity> = emptyList(),
    @SerialName("loanRecords")
    val loanRecords: List<LoanRecordEntity> = emptyList(),
    @SerialName("loans")
    val loans: List<LoanEntity> = emptyList(),
    @SerialName("plannedPaymentRules")
    val plannedPaymentRules: List<PlannedPaymentRuleEntity> = emptyList(),
    @SerialName("settings")
    val settings: List<SettingsEntity> = emptyList(),
    @SerialName("transactions")
    val transactions: List<TransactionEntity> = emptyList(),
    @SerialName("sharedPrefs")
    val sharedPrefs: HashMap<String, String> = HashMap(),
    @SerialName("tags")
    val tags: List<TagEntity> = emptyList(),
    @SerialName("tagAssociations")
    val tagAssociations: List<TagAssociationEntity> = emptyList()
)

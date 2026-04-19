package com.fern.legacy.datamodel.temp

import com.fern.base.legacy.LegacyTag
import com.fern.base.legacy.LegacyTransaction
import com.fern.data.db.entity.TransactionEntity
import com.fern.data.model.Tag
import com.fern.data.model.Transaction
import com.fern.data.repository.mapper.TransactionMapper
import com.fern.legacy.datamodel.toEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

fun Transaction.toLegacy(mapper: TransactionMapper): LegacyTransaction {
    return with(mapper) { toEntity().toLegacyDomain() }
}

suspend fun LegacyTransaction.toDomain(mapper: TransactionMapper): Transaction? {
   return with(mapper) {
       toEntity().toDomain().getOrNull()
    }
}

fun TransactionEntity.toLegacyDomain(
    tags: ImmutableList<LegacyTag> = persistentListOf()
): LegacyTransaction = LegacyTransaction(
    accountId = accountId,
    type = type,
    amount = amount.toBigDecimal(),
    toAccountId = toAccountId,
    toAmount = toAmount?.toBigDecimal() ?: amount.toBigDecimal(),
    title = title,
    description = description,
    dateTime = dateTime,
    categoryId = categoryId,
    dueDate = dueDate,
    recurringRuleId = recurringRuleId,
    paidFor = paidForDateTime,
    attachmentUrl = attachmentUrl,
    loanId = loanId,
    loanRecordId = loanRecordId,
    id = id,
    tags = tags
)

fun Tag.toLegacyTag(): LegacyTag = LegacyTag(this.id.value, this.name.value)
fun List<Tag>.toImmutableLegacyTags(): ImmutableList<LegacyTag> =
    this.map { it.toLegacyTag() }.toImmutableList()

fun TransactionEntity.isIdenticalWith(transaction: TransactionEntity?): Boolean {
    if (transaction == null) return false

    // Set isSynced && isDeleted to false so they aren't accounted in the equals check
    return this.copy(
        isSynced = false,
        isDeleted = false
    ) == transaction.copy(
        isSynced = false,
        isDeleted = false
    )
}

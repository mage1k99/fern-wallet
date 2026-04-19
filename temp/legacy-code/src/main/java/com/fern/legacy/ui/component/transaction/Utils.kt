package com.fern.legacy.ui.component.transaction

import androidx.compose.runtime.Composable
import com.fern.data.model.Category
import com.fern.legacy.datamodel.Account
import com.fern.legacy.ivyWalletCtx
import java.util.UUID

@Deprecated("Old design system. Use `:fern-design` and Material3")
@Composable
fun category(
    categoryId: UUID?,
    categories: List<Category>
): Category? {
    val targetId = categoryId ?: return null
    return ivyWalletCtx().categoryMap[targetId] ?: categories.find { it.id.value == targetId }
}

@Deprecated("Old design system. Use `:fern-design` and Material3")
@Composable
fun account(
    accountId: UUID?,
    accounts: List<Account>
): Account? {
    val targetId = accountId ?: return null
    return ivyWalletCtx().accountMap[targetId] ?: accounts.find { it.id == targetId }
}

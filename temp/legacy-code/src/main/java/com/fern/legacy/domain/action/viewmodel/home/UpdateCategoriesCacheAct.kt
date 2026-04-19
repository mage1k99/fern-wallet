package com.fern.wallet.domain.action.viewmodel.home

import com.fern.data.model.Category
import com.fern.frp.action.FPAction
import com.fern.legacy.IvyWalletCtx
import javax.inject.Inject

class UpdateCategoriesCacheAct @Inject constructor(
    private val ivyWalletCtx: IvyWalletCtx
) : FPAction<List<Category>, List<Category>>() {
    override suspend fun List<Category>.compose(): suspend () -> List<Category> = suspend {
        val categories = this

        ivyWalletCtx.categoryMap.clear()
        ivyWalletCtx.categoryMap.putAll(categories.map { it.id.value to it })

        categories
    }
}

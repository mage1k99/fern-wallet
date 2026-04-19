package com.fern.wallet.domain.action.account

import com.fern.data.db.dao.read.AccountDao
import com.fern.frp.action.FPAction
import com.fern.frp.then
import com.fern.legacy.datamodel.Account
import com.fern.legacy.datamodel.temp.toLegacyDomain
import java.util.UUID
import javax.inject.Inject

class AccountByIdAct @Inject constructor(
    private val accountDao: AccountDao
) : FPAction<UUID, Account?>() {
    @Deprecated("Legacy code. Don't use it, please.")
    override suspend fun UUID.compose(): suspend () -> Account? = suspend {
        this // accountId
    } then accountDao::findById then {
        it?.toLegacyDomain()
    }
}

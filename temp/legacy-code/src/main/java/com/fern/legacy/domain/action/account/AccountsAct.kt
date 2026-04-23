package com.fern.wallet.domain.action.account

import com.fern.data.db.dao.read.AccountDao
import com.fern.frp.action.FPAction
import com.fern.legacy.datamodel.Account
import com.fern.legacy.datamodel.temp.toLegacyDomain
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class AccountsAct @Inject constructor(
    private val accountDao: AccountDao
) : FPAction<Unit, ImmutableList<Account>>() {

    override suspend fun Unit.compose(): suspend () -> ImmutableList<Account> = suspend {
        io { accountDao.findAllWithArchived().map { it.toLegacyDomain() }.toImmutableList() }
    }
}

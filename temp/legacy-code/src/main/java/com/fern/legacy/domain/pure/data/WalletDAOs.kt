package com.fern.wallet.domain.pure.data

import com.fern.data.db.dao.read.AccountDao
import com.fern.data.db.dao.read.ExchangeRatesDao
import com.fern.data.db.dao.read.TransactionDao
import javax.inject.Inject

data class WalletDAOs @Inject constructor(
    val accountDao: AccountDao,
    val transactionDao: TransactionDao,
    val exchangeRatesDao: ExchangeRatesDao
)

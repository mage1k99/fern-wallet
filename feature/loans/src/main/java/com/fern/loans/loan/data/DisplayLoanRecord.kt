package com.fern.loans.loan.data

import com.fern.legacy.datamodel.Account
import com.fern.legacy.datamodel.LoanRecord

data class DisplayLoanRecord(
    val loanRecord: LoanRecord,
    val account: Account? = null,
    val loanRecordCurrencyCode: String = "",
    val loanCurrencyCode: String = "",
    val loanRecordTransaction: Boolean = false,
)

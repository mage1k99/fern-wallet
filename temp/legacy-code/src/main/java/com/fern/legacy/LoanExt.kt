package com.fern.legacy

import com.fern.base.legacy.stringRes
import com.fern.data.model.LoanType
import com.fern.legacy.datamodel.Loan
import com.fern.ui.R

fun Loan.humanReadableType(): String {
    return if (type == LoanType.BORROW) {
        stringRes(R.string.borrowed_uppercase)
    } else {
        stringRes(R.string.lent_uppercase)
    }
}

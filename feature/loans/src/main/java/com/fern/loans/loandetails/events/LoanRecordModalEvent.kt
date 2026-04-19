package com.fern.loans.loandetails.events

import com.fern.legacy.datamodel.LoanRecord
import com.fern.loans.loan.data.DisplayLoanRecord
import com.fern.wallet.domain.deprecated.logic.model.CreateLoanRecordData
import com.fern.wallet.domain.deprecated.logic.model.EditLoanRecordData

sealed interface LoanRecordModalEvent : LoanDetailsScreenEvent {
    data class OnClickLoanRecord(val displayLoanRecord: DisplayLoanRecord) : LoanRecordModalEvent
    data class OnCreateLoanRecord(val loanRecordData: CreateLoanRecordData) :
        LoanRecordModalEvent

    data class OnDeleteLoanRecord(val loanRecord: LoanRecord) : LoanRecordModalEvent
    data class OnEditLoanRecord(val loanRecordData: EditLoanRecordData) : LoanRecordModalEvent
    data object OnDismissLoanRecord : LoanRecordModalEvent

    data object OnChangeDate : LoanRecordModalEvent
    data object OnChangeTime : LoanRecordModalEvent
}

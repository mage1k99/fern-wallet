package com.fern.accounts

sealed interface AccountsEvent {
    data class OnReorder(val reorderedList: List<com.fern.legacy.data.model.AccountData>) :
        AccountsEvent
    data class OnReorderModalVisible(val reorderVisible: Boolean) : AccountsEvent
}

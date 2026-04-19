package com.fern.domain

import android.content.Intent
import com.fern.base.model.TransactionType

/**
 * A component used to start the **RootActivity** without knowing about it.
 */
interface AppStarter {
    fun getRootIntent(): Intent
    fun defaultStart()
    fun addTransactionStart(type: TransactionType)
}

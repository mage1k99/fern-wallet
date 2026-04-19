package com.fern.wallet.migrations

interface Migration {
    val key: String

    suspend fun migrate()
}

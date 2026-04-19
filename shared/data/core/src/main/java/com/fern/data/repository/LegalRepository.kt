package com.fern.data.repository

import com.fern.base.threading.DispatchersProvider
import com.fern.data.datasource.LocalLegalDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LegalRepository @Inject constructor(
    private val localLegalDataSource: LocalLegalDataSource,
    private val dispatchers: DispatchersProvider
) {
    suspend fun isDisclaimerAccepted(): Boolean = withContext(dispatchers.io) {
        localLegalDataSource.getIsDisclaimerAccepted() ?: false
    }

    suspend fun setDisclaimerAccepted(
        accepted: Boolean
    ): Unit = withContext(dispatchers.io) {
        localLegalDataSource.setDisclaimerAccepted(accepted)
    }
}
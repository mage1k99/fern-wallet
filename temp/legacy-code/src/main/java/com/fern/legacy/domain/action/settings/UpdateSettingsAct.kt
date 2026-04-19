package com.fern.legacy.domain.action.settings

import com.fern.data.db.dao.write.WriteSettingsDao
import com.fern.frp.action.FPAction
import com.fern.legacy.datamodel.Settings
import javax.inject.Inject

class UpdateSettingsAct @Inject constructor(
    private val writeSettingsDao: WriteSettingsDao
) : FPAction<Settings, Settings>() {
    override suspend fun Settings.compose(): suspend () -> Settings = suspend {
        writeSettingsDao.save(this.toEntity())
        this
    }
}

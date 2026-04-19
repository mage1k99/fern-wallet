package com.fern.legacy.datamodel.temp

import com.fern.data.db.entity.SettingsEntity
import com.fern.legacy.datamodel.Settings

fun SettingsEntity.toLegacyDomain(): Settings = Settings(
    theme = theme,
    baseCurrency = currency,
    bufferAmount = bufferAmount.toBigDecimal(),
    name = name,
    id = id
)

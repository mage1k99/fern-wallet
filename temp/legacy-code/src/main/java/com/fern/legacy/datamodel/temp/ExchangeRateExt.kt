package com.fern.legacy.datamodel.temp

import com.fern.data.db.entity.ExchangeRateEntity
import com.fern.legacy.datamodel.ExchangeRate

fun ExchangeRateEntity.toLegacyDomain(): ExchangeRate = ExchangeRate(
    baseCurrency = baseCurrency,
    currency = currency,
    rate = rate
)

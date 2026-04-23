package com.fern.legacy.datamodel.temp

import com.fern.data.db.entity.AccountEntity
import com.fern.legacy.datamodel.Account

fun AccountEntity.toLegacyDomain(): Account = Account(
    name = name,
    currency = currency,
    color = color,
    icon = icon,
    orderNum = orderNum,
    includeInBalance = includeInBalance,
    isArchived = isArchived,
    isSynced = isSynced,
    isDeleted = isDeleted,
    id = id
)

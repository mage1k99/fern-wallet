package com.fern.data.model.testing

import com.fern.data.model.AccountId
import com.fern.data.model.CategoryId
import com.fern.data.model.TransactionId
import java.util.UUID

object ModelFixtures {
    val AccountId = AccountId(UUID.randomUUID())
    val CategoryId = CategoryId(UUID.randomUUID())
    val TransactionId = TransactionId(UUID.randomUUID())
}

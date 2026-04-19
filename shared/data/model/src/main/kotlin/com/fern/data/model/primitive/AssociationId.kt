package com.fern.data.model.primitive

import com.fern.data.model.sync.UniqueId
import java.util.UUID

@JvmInline
value class AssociationId(override val value: UUID) : UniqueId
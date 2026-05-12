package com.fern.data.model

import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.ColorInt
import com.fern.data.model.primitive.IconAsset
import com.fern.data.model.primitive.NotBlankTrimmedString
import com.fern.data.model.sync.Identifiable
import com.fern.data.model.sync.UniqueId
import java.util.UUID

@JvmInline
value class AccountId(override val value: UUID) : UniqueId

data class Account(
    override val id: AccountId,
    val name: NotBlankTrimmedString,
    val asset: AssetCode,
    val color: ColorInt,
    val icon: IconAsset?,
    val includeInBalance: Boolean,
    val isArchived: Boolean,
    override val orderNum: Double,
) : Identifiable<AccountId>, Reorderable

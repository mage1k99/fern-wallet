package com.fern.legacy.datamodel

import androidx.compose.runtime.Immutable
import arrow.core.Either
import arrow.core.raise.either
import com.fern.data.db.entity.AccountEntity
import com.fern.data.model.Account
import com.fern.data.model.AccountId
import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.ColorInt
import com.fern.data.model.primitive.IconAsset
import com.fern.data.model.primitive.NotBlankTrimmedString
import com.fern.data.repository.CurrencyRepository
import java.util.UUID
import com.fern.data.model.Account as DomainAccount

@Deprecated("Legacy data model. Will be deleted")
@Immutable
data class Account(
    val name: String,
    val color: Int,
    val currency: String? = null,
    val icon: String? = null,
    val orderNum: Double = 0.0,
    val includeInBalance: Boolean = true,
    val isArchived: Boolean = false,

    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,

    val id: UUID = UUID.randomUUID()
) {
    fun toEntity(): AccountEntity = AccountEntity(
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

    @Suppress("DataClassFunctions")
    suspend fun toDomainAccount(
        currencyRepository: CurrencyRepository
    ): Either<String, DomainAccount> {
        return either {
            Account(
                id = AccountId(id),
                name = NotBlankTrimmedString.from(name).bind(),
                asset = currency?.let(AssetCode::from)?.bind()
                    ?: currencyRepository.getBaseCurrency(),
                color = ColorInt(color),
                icon = icon?.let(IconAsset::from)?.getOrNull(),
                includeInBalance = includeInBalance,
                isArchived = isArchived,
                orderNum = orderNum,
            )
        }
    }
}

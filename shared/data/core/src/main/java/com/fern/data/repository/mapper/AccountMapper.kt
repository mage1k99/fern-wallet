package com.fern.data.repository.mapper

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.fern.data.db.entity.AccountEntity
import com.fern.data.model.Account
import com.fern.data.model.AccountId
import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.ColorInt
import com.fern.data.model.primitive.IconAsset
import com.fern.data.model.primitive.NotBlankTrimmedString
import com.fern.data.repository.CurrencyRepository
import javax.inject.Inject

class AccountMapper @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend fun AccountEntity.toDomain(): Either<String, Account> = either {
        ensure(!isDeleted) { "Account is deleted" }

        Account(
            id = AccountId(id),
            name = NotBlankTrimmedString.from(name).bind(),
            asset = currency?.let(AssetCode::from)?.getOrNull()
                ?: currencyRepository.getBaseCurrency(),
            color = ColorInt(color),
            icon = icon?.let(IconAsset::from)?.getOrNull(),
            includeInBalance = includeInBalance,
            isArchived = isArchived,
            orderNum = orderNum,
        )
    }

    fun Account.toEntity(): AccountEntity {
        return AccountEntity(
            name = name.value,
            currency = asset.code,
            color = color.value,
            icon = icon?.id,
            orderNum = orderNum,
            includeInBalance = includeInBalance,
            isArchived = isArchived,
            id = id.value,
            isSynced = true, // TODO: Delete this
        )
    }
}

package com.fern.wallet.domain.action.wallet

import arrow.core.toOption
import com.fern.data.model.Account
import com.fern.data.model.AccountId
import com.fern.data.model.primitive.AssetCode
import com.fern.data.model.primitive.ColorInt
import com.fern.data.model.primitive.IconAsset
import com.fern.data.model.primitive.NotBlankTrimmedString
import com.fern.frp.action.FPAction
import com.fern.frp.action.thenFilter
import com.fern.frp.action.thenMap
import com.fern.frp.action.thenSum
import com.fern.frp.fixUnit
import com.fern.wallet.domain.action.account.AccountsAct
import com.fern.wallet.domain.action.account.CalcAccBalanceAct
import com.fern.wallet.domain.action.exchange.ExchangeAct
import com.fern.wallet.domain.pure.data.ClosedTimeRange
import com.fern.wallet.domain.pure.exchange.ExchangeData
import java.math.BigDecimal
import javax.inject.Inject

class CalcWalletBalanceAct @Inject constructor(
    private val accountsAct: AccountsAct,
    private val calcAccBalanceAct: CalcAccBalanceAct,
    private val exchangeAct: ExchangeAct,
) : FPAction<CalcWalletBalanceAct.Input, BigDecimal>() {

    override suspend fun Input.compose(): suspend () -> BigDecimal = recipe().fixUnit()

    private suspend fun Input.recipe(): suspend (Unit) -> BigDecimal =
        accountsAct thenFilter {
            withExcluded || it.includeInBalance
        } thenMap { account ->
            calcAccBalanceAct(
                CalcAccBalanceAct.Input(
                    account = Account(
                        id = AccountId(account.id),
                        name = NotBlankTrimmedString.from(account.name).getOrNull()
                            ?: error("account name cannot be blank"),
                        asset = AssetCode.from(account.currency ?: baseCurrency).getOrNull()
                            ?: error("account currency cannot be blank"),
                        color = ColorInt(account.color),
                        icon = account.icon?.let { IconAsset.from(it).getOrNull() },
                        includeInBalance = account.includeInBalance,
                        orderNum = account.orderNum,
                    ),
                    range = range
                )
            )
        } thenMap {
            exchangeAct(
                ExchangeAct.Input(
                    data = ExchangeData(
                        baseCurrency = baseCurrency,
                        fromCurrency = (it.account.asset.code).toOption(),
                        toCurrency = balanceCurrency
                    ),
                    amount = it.balance
                )
            )
        } thenSum {
            it.orNull() ?: BigDecimal.ZERO
        }

    @Suppress("DataClassDefaultValues")
    data class Input(
        val baseCurrency: String,
        val balanceCurrency: String = baseCurrency,
        val range: ClosedTimeRange? = null,
        val withExcluded: Boolean = false
    )
}

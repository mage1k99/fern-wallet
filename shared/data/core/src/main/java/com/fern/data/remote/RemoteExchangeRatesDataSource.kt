package com.fern.data.remote

import arrow.core.Either
import com.fern.data.remote.responses.ExchangeRatesResponse

interface RemoteExchangeRatesDataSource {
    suspend fun fetchEurExchangeRates(): Either<String, ExchangeRatesResponse>
}

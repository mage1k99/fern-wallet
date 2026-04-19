package com.fern.wallet.domain.data

interface Reorderable {
    fun getItemOrderNum(): Double

    fun withNewOrderNum(newOrderNum: Double): Reorderable
}

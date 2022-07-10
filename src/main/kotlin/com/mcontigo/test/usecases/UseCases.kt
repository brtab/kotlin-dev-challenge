package com.mcontigo.test.usecases

import com.mcontigo.test.BTCPrice
import com.mcontigo.test.FiatType

interface UseCases {
    fun fetchAll(): List<BTCPrice>
    fun fetchBtcPriceByFiat(type: FiatType): BTCPrice
    fun syncBtcPrice()
}
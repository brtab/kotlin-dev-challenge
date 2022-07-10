package com.mcontigo.test.usecases

import com.mcontigo.test.BTCPrice
import com.mcontigo.test.FiatType
import com.mcontigo.test.Repo
import com.mcontigo.test.RepositoryDB
import com.mcontigo.test.exception.BusinessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class IUseCases : UseCases {

    @Autowired
    val repository: Repo? = null

    @Throws(BusinessException::class)
    override fun fetchAll(): List<BTCPrice> {
        try {
            return repository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }


    override fun fetchBtcPriceByFiat(type: FiatType): BTCPrice {
        return BTCPrice(BigDecimal(11111.111), LocalDateTime.now(), FiatType.EUR)
    }

    @Throws(BusinessException::class)
    override fun syncBtcPrice() {
        try {
            val price = BTCPrice(
                BigDecimal(20841.7069),
                LocalDateTime.now(),
                FiatType.USD
            )
            repository!!.save(price)
        } catch (e: Exception) {
            BusinessException(e.message)
        }
    }
}
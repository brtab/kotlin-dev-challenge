package com.mcontigo.test

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="btcprice")
data class BTCPrice (
    @Column(name="price")
    val price: BigDecimal,
    @Column(name="update")
    val lastUpdate: LocalDateTime,
    @Column(name="type")
    val fiatType: FiatType
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}

enum class FiatType {
    USD, GBP, EUR
}

package com.mcontigo.test

import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.LocalDateTime

/*
You should implement this interface to interact with persistence
If you prefer, can use any ORM library
*/
@Repository
interface RepositoryDB {
    @Query(value = "select * " + "from btcprice " + "order by btcprice.update", nativeQuery = true)
    fun fetchAll(): List<BTCPrice>

    @Query(value = "select * " + "from btcprice " + "where btcprice.type = :fiatType", nativeQuery = true)
    fun fetchByFiat(fiatType: FiatType): BTCPrice

    @Query(value="insert into btcprice(price, update, type) " + "values(:price, :registerAt, :fiatType")
    fun registerBTCPrice(price: BigDecimal, registerAt: LocalDateTime, fiatType: FiatType)
}

/*
 You should implement this based on coindesk request from https://api.coindesk.com/v1/bpi/currentprice.json
 */
interface RepositoryExternal {
    fun fetchCurrentPrice(): List<BTCPrice>
}

@Repository
interface Repo : JpaRepository<BTCPrice, Long>
package com.mcontigo.test.web

import com.mcontigo.test.BTCPrice
import com.mcontigo.test.FiatType
import com.mcontigo.test.exception.BusinessException
import com.mcontigo.test.exception.NotFoundException
import com.mcontigo.test.usecases.UseCases
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("btc")
class BTCController { /*
    You should implement all resources of API requests here
    GET:
        / - retrieve all btc fiat pairs registered ordered by date
        /pair/:fiatType - retrieve current value of pair BTC-fiatType
    POST:
        / - execute a task to fetch btc price and persist on database
     */

    @Autowired
    val useCases: UseCases? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<BTCPrice>> {
        return try {
            ResponseEntity(useCases!!.fetchAll(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/pair/{fiatType}")
    fun load(@PathVariable("fiatType") type: FiatType): ResponseEntity<Any> {
        return try {
            ResponseEntity(useCases!!.fetchBtcPriceByFiat(type), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun fetch(): ResponseEntity<Any> {
        return try {
            useCases!!.syncBtcPrice()
            ResponseEntity(HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
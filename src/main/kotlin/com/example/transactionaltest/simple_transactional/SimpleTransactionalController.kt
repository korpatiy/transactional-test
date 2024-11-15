package com.example.transactionaltest.simple_transactional

import com.example.transactionaltest.entity.AccountEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/simple-transactional")
class SimpleTransactionalController(
    private val simpleTransactionalService: SimpleTransactionalService,
) {

    @PutMapping("/0")
    fun updateAccount0(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney0(accountId)
    }

    @PutMapping("/1")
    fun updateAccount1(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney1(accountId)
    }

    @PutMapping("/2")
    fun updateAccount2(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney2(accountId)
    }

    @PutMapping("/3")
    fun updateAccount3(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney3(accountId)
    }

    @PutMapping("/4")
    fun updateAccount4(@RequestParam accountId: Long): AccountEntity {
        return simpleTransactionalService.addHundredMoney4(accountId)
    }

    @PutMapping("/5")
    fun updateAccount5(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney5(accountId)
    }

    @PutMapping("/6")
    fun updateAccount6(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney6(accountId)
    }

    @PutMapping("/7")
    fun updateAccount7(@RequestParam accountId: Long) {
        simpleTransactionalService.addHundredMoney7(accountId)
    }

    @PutMapping("/8")
    fun addHundredMoney8() {
        simpleTransactionalService.addHundredMoney8()
    }


    @GetMapping("/cache")
    fun testCache() {
        simpleTransactionalService.testCache(simpleTransactionalService.findById().get())
    }
}


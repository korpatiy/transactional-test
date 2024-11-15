package com.example.transactionaltest.inject_transactional

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/inject-transactional")
class InjectTransactionalController(private val injectTransactionalService: InjectTransactionalService) {

    @PutMapping("/0")
    fun updateAccount4(@RequestParam accountId: Long) {
        injectTransactionalService.addHundredMoneyOpen0(accountId)
    }

    @PutMapping("/1")
    fun updateAccount5(@RequestParam accountId: Long) {
        injectTransactionalService.addHundredMoneyOpen1(accountId)
    }
}
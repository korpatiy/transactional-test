package com.example.transactionaltest.isolation

import com.example.transactionaltest.entity.AccountEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/isolation")
class IsolationController(private val isolationService: IsolationService) {

    @PutMapping("/0")
    fun first(): AccountEntity {
        return isolationService.repeatableRead()
    }

    @PutMapping("/1")
    fun second(): Int {
        return isolationService.serializable().count()

    }

    @PutMapping("/2")
    fun third() {
        isolationService.addHundred()
    }

    @PutMapping("/3")
    fun fourth() {
        isolationService.addAccount()
    }
}

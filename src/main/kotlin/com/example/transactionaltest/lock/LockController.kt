package com.example.transactionaltest.lock

import com.example.transactionaltest.entity.AccountEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/lock")
class LockController(private val lockService: LockService) {

    @GetMapping("/0")
    fun getLock(): AccountEntity {
        return lockService.getLock()
    }

    @GetMapping("/1")
    fun getWithoutLock(): List<AccountEntity> {
        return lockService.getWithoutLock()
    }
}
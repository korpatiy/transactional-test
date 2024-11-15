package com.example.transactionaltest.erorrs

import com.example.transactionaltest.entity.AccountEntity
import com.example.transactionaltest.repo.AccountEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


@Service
class ErrorTransactionalService(private val accountEntityRepository: AccountEntityRepository) {

    @Lazy
    @Autowired
    private lateinit var errorTransactionalService: ErrorTransactionalService

    @Transactional(readOnly = true)
    fun readOnly(): AccountEntity {
        val accountEntity = accountEntityRepository.findById(1).get()
        accountEntity.balance += 100
        return accountEntity
        //return accountEntityRepository.save(accountEntity)
        //accountEntityRepository.addBalanceById(1, 100)
    }

    @Transactional(timeout = 2)
    fun timeOut() {
        errorTransactionalService.noSleepNew()
        errorTransactionalService.sleepNew()
    }

    @Transactional
    fun sleepNew() {
        val accountEntity = accountEntityRepository.findById(1).get()
        accountEntity.balance += 100
        accountEntityRepository.save(accountEntity)
        Thread.sleep(3000)
    }

    @Transactional
    fun noSleepNew() {
        val accountEntity = accountEntityRepository.findById(1).get()
        accountEntity.balance += 100
        accountEntityRepository.save(accountEntity)
    }

    @Transactional
    fun rollbackFor() {
        errorTransactionalService.simpleUpdateAccount()
        errorTransactionalService.updateAccountWithError()
    }

    @Transactional
    fun simpleUpdateAccount() {
        val accountEntity = accountEntityRepository.findById(1).get()
        accountEntity.balance += 100
        accountEntityRepository.save(accountEntity)
    }

    @Transactional
    fun updateAccountWithError() {
        val accountEntity = accountEntityRepository.findById(1).get()
        accountEntity.balance += 100
        accountEntityRepository.save(accountEntity)
        throw IllegalArgumentException("error")
    }
}
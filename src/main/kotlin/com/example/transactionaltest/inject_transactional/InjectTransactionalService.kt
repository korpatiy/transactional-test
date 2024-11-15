package com.example.transactionaltest.inject_transactional

import com.example.transactionaltest.repo.AccountEntityRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


@Service
class InjectTransactionalService(
    private val repository: AccountEntityRepository,
    //@Lazy private val injectTransactionalService: InjectTransactionalService?,
) {

    @Lazy
    @Autowired
    private lateinit var injectTransactionalService: InjectTransactionalService

    @Transactional()
    fun addHundredMoney(accountId: Long) {
        val account = repository.findById(accountId).get()
        account.balance += 100
    }

    fun addHundredMoneyOpen0(accountId: Long) {
        addHundredMoney(accountId)
    }

    fun addHundredMoneyOpen1(accountId: Long) {
        injectTransactionalService.addHundredMoney(accountId)
    }
}
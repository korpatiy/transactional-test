package com.example.transactionaltest.propagation

import com.example.transactionaltest.repo.AccountEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PropagationService(private val subPropagationService: SubPropagationService) {

    @Transactional
    fun propagation() {
        subPropagationService.addHundredMoney()
    }
}

@Service
class SubPropagationService(private val repository: AccountEntityRepository) {

    @Transactional(propagation = Propagation.NESTED)
    fun addHundredMoney() {
        val account = repository.findById(1).get()
        account.balance += 100
        repository.save(account)
    }
}
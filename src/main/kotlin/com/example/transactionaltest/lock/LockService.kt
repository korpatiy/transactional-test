package com.example.transactionaltest.lock

import com.example.transactionaltest.entity.AccountEntity
import com.example.transactionaltest.repo.AccountEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional


// Много потоков доступ 1 сущности //
// Мало редко доступ 1 сущности //

@Service
class LockService(
    private val repository: AccountEntityRepository,
) {

    @Transactional
    fun getLock(): AccountEntity {
        println("getLock() in")
        val account = repository.customFindById(1)
        account.balance += 100
        repository.save(account)
        Thread.sleep(5_000)
        return account.also { println("getLock() out") }
    }

    @Transactional
    fun getWithoutLock(): List<AccountEntity> {
        val entities = repository.findAllCustom()
        entities.forEach { it.balance += 100 }
        repository.saveAll(entities)
        return entities


        /*val accountEntity = repository.findAll()
        return accountEntity.also {
            println("getWithoutLock() out")
        }*/
        //accountEntity.forEach { it.balance += 100 }
//        repository.saveAll(accountEntity)
        ///repository.save(accountEntity.also { it.balance += 100 })
    }
}
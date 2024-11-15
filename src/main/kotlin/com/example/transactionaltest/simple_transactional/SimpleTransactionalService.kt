package com.example.transactionaltest.simple_transactional

import com.example.transactionaltest.entity.AccountEntity
import com.example.transactionaltest.repo.AccountEntityRepository
import com.example.transactionaltest.repo.OrganizationEntityRepository
import org.hibernate.context.spi.CurrentSessionContext
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


//final -> @Service -> open
@Service
class SimpleTransactionalService(
    private val accountEntityRepository: AccountEntityRepository,
    private val organizationEntityRepository: OrganizationEntityRepository,
) {

    fun findById() = accountEntityRepository.findById(1)

    @Transactional
    fun addHundredMoney0(accountId: Long) {
        println("Method In")
        val account = accountEntityRepository.findById(accountId).get()
        account.balance += 100
        accountEntityRepository.save(account)
        println("Method out")
    }

    fun addHundredMoney1(accountId: Long) {
        println("Method In")
        val account = accountEntityRepository.findById(accountId).get()
        account.balance += 100
        println("Method out")
    }

    @Transactional
    fun addHundredMoney2(accountId: Long) {
        println("Method In")
        val account = accountEntityRepository.findById(accountId).get()
        account.balance += 100
        println("Method out")
    }

    fun addHundredMoney3(accountId: Long) {
        println("Method In")
        val account = accountEntityRepository.findById(accountId).get()
        account.balance += 100
        accountEntityRepository.save(account)
        println("Method out")
    }

    @Transactional
    fun addHundredMoney4(accountId: Long): AccountEntity {
        println("Method In")
        val get = accountEntityRepository.findById(accountId).get()
        //accountEntityRepository.addBalanceById(1, 100)
        //get.balance += 100
        println("Method out and return")
        return accountEntityRepository.findById(accountId).get()
    }

    @Transactional
    fun addHundredMoney5(accountId: Long) {
        println("Method In")
        accountEntityRepository.addBalanceById(accountId, 100)
        println("Method out")
    }

    @Transactional
    fun testCache(accountEntity: AccountEntity){
        accountEntity.balance += 100
    }

    /* ------------------------------------------------------------------- */
    //readlny, follbafor, norolback, timeout, lock, cascade+fetch

    @Transactional
    fun addHundredMoney6(accountId: Long) {
        println("Method In")
        val account = accountEntityRepository.findById(accountId).get()
        account.balance += 100
        account.organization.balance += 100
        println("Method out")
    }

    @Transactional
    fun addHundredMoney7(accountId: Long) {
        println("Method In")
        val org = organizationEntityRepository.findById(1).get()
        //org.users.forEach { it.balance += 100 }
        //org.balance = org.users.sumOf { it.balance }
        println("Method out")
    }

    @Transactional
    fun addHundredMoney8() {
        //accountEntityRepository.save(AccountEntity(userName = "kekov", balance = 100L, organization = org)))
        val org = organizationEntityRepository.findById(1).get()
        /* org.users.add(
             accountEntityRepository.save(
                 AccountEntity(
                     userName = "kekov",
                     balance = 100L,
                     organization = org
                 )
             )
         )*/
        //organizationEntityRepository.save(org)
    }
}
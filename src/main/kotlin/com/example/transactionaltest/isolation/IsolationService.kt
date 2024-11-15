package com.example.transactionaltest.isolation

import com.example.transactionaltest.entity.AccountEntity
import com.example.transactionaltest.repo.AccountEntityRepository
import com.example.transactionaltest.repo.OrganizationEntityRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.util.*



//Read uncommitted - чтение незафиксированных данных;
//Read committed - чтение зафиксированных данных; --dirty read -
//Repeatable read - повторяющееся чтение; -- non-repetable read
//Serializable - упорядочиваемость. -- phantom

// dirty read -
//tx1  //tx
// Transaction 1                                    | Transaction 2
// SELECT value FROM test_table WHERE id=1; -- (10) |
//                                                  | UPDATE test_table SET value=20 WHERE id=1;
// SELECT value FROM test_table WHERE id=1; -- (20) |
                                                     // rolbback
// repetable read
// Transaction 1                                    | Transaction 2
// SELECT value FROM test_table WHERE id=1; -- (10) |
//                                                  | UPDATE test_table SET value=20 WHERE id=1;
//                                                  | COMMIT;
// SELECT value FROM test_table WHERE id=1; -- (20) |
//
// phantom read
// Transaction 1                                    | Transaction 2
// SELECT value FROM test_table WHERE id>0; -- (10) |
//                                                  | INSERT INTO test_table VALUES (2, 20);
//                                                  | COMMIT;
// SELECT value FROM test_table WHERE id>0;         |
//-- (10) !! doesn't occurred                       |
// UPDATE test_table SET value 30 WHERE id=2;       |
// SELECT value FROM test_table WHERE id>1;         |
//-- (10, 30) !! occurred

@Service
class IsolationService(
    private val repository: AccountEntityRepository,
    private val organizationEntityRepository: OrganizationEntityRepository,
    private val entityManager: EntityManager,
) {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun repeatableRead(): AccountEntity {
        //m2m ---
        println("TX IN --->>>>")
        val accountEntity = repository.findById(1).get()
        //entityManager.detach(accountEntity)

        println("stream read - $accountEntity")
        accountEntity.balance += 100
        Thread.sleep(5000)
        val save = repository.save(accountEntity)
        println("stream updated - $save")
        println("TX stream out <<<<---")
        //600
        return save
    }

    @Transactional
    fun addHundred() {
        val accountEntity = repository.findById(1).get()
        accountEntity.balance += 100
        repository.save(accountEntity)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun serializable(): List<AccountEntity> {
        println("TX stream IN --->>>>")
        val accountEntities = repository.findAllHundredOrMore()
        println("stream read count - ${accountEntities.count()}")
        /*    for (accountEntity in accountEntities) {
                accountEntity.balance += 100
            }*/
        Thread.sleep(5000)
        //repository.addHundredForAllAccounts()
        //val findAllHundredOrMore = repository.findAllHundredOrMore()
        //println("stream updated - ${findAllHundredOrMore.count()}")
        //println("TX streamout <<<<---")
        return repository.findAll()
    }

    @Transactional
    fun addAccount() {
        repository.deleteById(63)
        /*   repository.save(
               AccountEntity(
                   userName = UUID.randomUUID().toString(),
                   balance = 100,
                   organization = organizationEntityRepository.findById(1).get()
               )
           )*/
        /* val accountEntity = repository.findById(4).get()
         accountEntity.balance -= 500
         repository.save(accountEntity)*/
    }
}
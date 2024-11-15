package com.example.transactionaltest.repo;

import com.example.transactionaltest.entity.AccountEntity
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

// tx1 Order for update -> 1db blocked -> commit
// tx2 Order read -> 1db
// tx3 Order for update -> BLOCKED
// tx4 Order for update -> BLOCKED
// tx5 Order for update -> BLOCKED
// tx6 Order for update -> BLOCKED

// Optimistic
// tx1 Order for update -> 1db blocked -> commit
// tx2 Order read -> 1db
// tx3 Order for update -> throw
// @Version version

@Repository
interface AccountEntityRepository : JpaRepository<AccountEntity, Long> {

    @Transactional
    @Modifying
    @Query("""update AccountEntity ae set ae.balance = ae.balance + :balance where ae.id = :id""")
    fun addBalanceById(id: Long, balance: Long)

    @Query("select ae from AccountEntity ae where ae.balance >= 100")
    fun findAllHundredOrMore(): List<AccountEntity>

    @Modifying
    @Query("update AccountEntity ae set ae.balance = ae.balance + 100 where ae.balance >= 100")
    fun addHundredForAllAccounts()

    @Lock(LockModeType.PESSIMISTIC_WRITE) //-- for share
    @Query("select ae from AccountEntity ae")
    fun findAllCustom(): List<AccountEntity>

    @Lock(LockModeType.PESSIMISTIC_WRITE) //-- for update
    @Query("select ae from AccountEntity ae where ae.id = :id")
    fun customFindById(id: Long): AccountEntity

    //LockModeType.PESSIMISTIC_READ - данные блокируются в момент ЧТЕНИЯ и это гарантирует, что никто в ходе выполнения транзакции не сможет их изменить. Остальные транзакции, тем не менее, смогут параллельно читать эти данные. Использование этой блокировки может вызывать долгое ожидание блокировки или даже выкидывание.
    //LockModeType.PESSIMISTIC_WRITE - данные блокируются в момент ЗАПИСИ и никто с момента захвата блокировки не может в них писать и не может их читать до окончания транзакции, владеющей блокировкой.
}
package com.example.transactionaltest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class TransactionalTestApplication

fun main(args: Array<String>) {
    runApplication<TransactionalTestApplication>(*args)
}

package com.example.transactionaltest.erorrs

import com.example.transactionaltest.entity.AccountEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/error-transactional")
class ErrorTransactionalController(private val errorTransactionalService: ErrorTransactionalService) {

    @GetMapping("/read-only")
    fun readOnly(): AccountEntity {
        return errorTransactionalService.readOnly()
    }

    @GetMapping("/timeout")
    fun timeOut0() {
        errorTransactionalService.timeOut()
    }

    @GetMapping("/noRollback")
    fun rollBackFor() {
        errorTransactionalService.rollbackFor()
    }
}
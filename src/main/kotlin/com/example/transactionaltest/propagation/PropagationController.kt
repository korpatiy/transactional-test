package com.example.transactionaltest.propagation

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/propagation")
class PropagationController(
    private val propagationService: PropagationService,
) {

    @PutMapping()
    fun required0() {
        propagationService.propagation()
    }
}
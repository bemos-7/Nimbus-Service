package com.bemos.nimbus.domain.use_cases

import com.bemos.nimbus.domain.repositories.KeyManagerRepository

class GetSharedKeyUseCase(
    private val keyManagerRepository: KeyManagerRepository
) {
    fun execute(): String {
        return keyManagerRepository.get()
    }
}
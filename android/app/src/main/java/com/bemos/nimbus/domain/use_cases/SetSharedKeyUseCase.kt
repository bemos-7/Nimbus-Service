package com.bemos.nimbus.domain.use_cases

import android.content.SharedPreferences
import com.bemos.nimbus.domain.repositories.KeyManagerRepository

class SetSharedKeyUseCase(
    private val repository: KeyManagerRepository
) {
    fun execute(key: String) {
        repository.set(key)
    }
}
package com.bemos.nimbus.domain.repositories

interface KeyManagerRepository {
    fun set(key: String)
    fun get(): String
}
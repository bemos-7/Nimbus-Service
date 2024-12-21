package com.bemos.nimbus.data.remote.local.shared_pref

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bemos.nimbus.domain.repositories.KeyManagerRepository
import com.bemos.nimbus.shared.Constants.SHARED_PREF_KEY

class KeyManagerImpl(
    private val sharedPreferences: SharedPreferences
) : KeyManagerRepository {
    override fun set(key: String) {
        sharedPreferences.edit {
            putString(SHARED_PREF_KEY, key)
        }
    }

    override fun get(): String {
        val key = sharedPreferences.getString(SHARED_PREF_KEY, "")
        return key.toString()
    }
}
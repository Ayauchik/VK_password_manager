package com.example.vkpasswordmanager.ui.masterKey

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MasterPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context get() = getApplication<Application>().applicationContext

    private val masterKeyExists: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val masterKey: MutableStateFlow<String?> = MutableStateFlow(null)

    private val password: MutableStateFlow<String?> = MutableStateFlow(null)

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            "master_key_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    init {
        viewModelScope.launch {
            val savedMasterKey = sharedPreferences.getString("master_key", null)
            masterKeyExists.value = savedMasterKey != null
            masterKey.value = savedMasterKey
        }
    }

    val isMasterKeyExists: StateFlow<Boolean> = masterKeyExists
    val masterKeyFlow: StateFlow<String?> = masterKey

    fun createMasterKey(key: String) {
        viewModelScope.launch {
            sharedPreferences.edit().putString("master_key", key).apply()
            masterKey.value = key
            masterKeyExists.value = true
        }
    }

    fun deleteMasterKey() {
        viewModelScope.launch {
            sharedPreferences.edit().putString("master_key", null).apply()
            masterKey.value = null
            masterKeyExists.value = false
        }
    }

    fun updateMasterKey(key: String){
        viewModelScope.launch {
            sharedPreferences.edit().putString("master_key", key).apply()
            masterKey.value = key
            masterKeyExists.value = true
        }
    }

    fun encryptPassword(password: String): String? {
        viewModelScope.launch {
            sharedPreferences.edit().putString("password", password).apply()
        }
        val encryptedPassword = sharedPreferences.getString("password", password)
        Log.e("encrypt", "$password $encryptedPassword")
        return encryptedPassword
    }
}

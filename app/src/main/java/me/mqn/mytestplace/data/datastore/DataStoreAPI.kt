package me.mqn.mytestplace.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import me.mqn.mytestplace.data.datastore.DataStoreConstants.dataStore
import java.io.IOException

abstract class DataStoreAPI(context: Context) {

    private val dataSource = context.applicationContext.dataStore

    protected suspend fun <T> putPreferenceValue(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    protected suspend fun <T> getPreferenceValue(key: Preferences.Key<T>, defaultValue: T): T =
        dataSource.data.first()[key] ?: defaultValue

    protected suspend fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    protected suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun clearAllPreference() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }
}
// for viewmodel
/*
// Fetching data via Flow
viewModelScope.launch {
    DataStoreManager(context).getUserId().collect {

    }
}

// Fetching data via static method
viewModelScope.launch {
   val userId =  DataStoreManager(context).getUserId()
}

// Saving Data
val userId = "6tqfe354gd"
viewModelScope.launch {
    DataStoreManager(context).saveUserId(userId)
}
* */

// For Activity/Fragment…
/*
// Fetching data via Flow
lifecycleScope.launch {
    DataStoreManager(context).isExistingUser().collect {

    }
}

// Fetching data via static method
lifecycleScope.launch {
   val existingUser =  DataStoreManager(context).isExistingUser()
}

// Saving Data
lifecycleScope.launch {
    DataStoreManager(context).saveExistingUser(true)
}
* */
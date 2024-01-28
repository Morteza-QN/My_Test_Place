package me.mqn.mytestplace.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreConstants {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_datastore")

    val ACCESS_JWT_KEY = stringPreferencesKey("access_jwt")
    val REFRESH_JWT_KEY = stringPreferencesKey("refresh_jwt")
}
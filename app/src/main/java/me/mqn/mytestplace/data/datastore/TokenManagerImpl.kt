package me.mqn.mytestplace.data.datastore

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import me.mqn.mytestplace.data.datastore.DataStoreConstants.ACCESS_JWT_KEY
import me.mqn.mytestplace.data.datastore.DataStoreConstants.REFRESH_JWT_KEY
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : TokenManager, DataStoreAPI(context) {

    override suspend fun saveAccessJwt(token: String) {
        putPreferenceValue(key = ACCESS_JWT_KEY, value = token)
    }

    override suspend fun saveRefreshJwt(token: String) {
        putPreferenceValue(key = REFRESH_JWT_KEY, value = token)
    }

    override suspend fun getAccessJwt(): String {
        return getPreferenceValue(key = ACCESS_JWT_KEY, defaultValue = "")
    }

    override suspend fun getRefreshJwt(): String {
        return getPreferenceValue(key = REFRESH_JWT_KEY, defaultValue = "")
    }

    override suspend fun clearAllTokens() {
        removePreference(key = ACCESS_JWT_KEY)
        removePreference(key = REFRESH_JWT_KEY)
    }
}
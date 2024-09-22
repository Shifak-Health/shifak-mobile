package com.mhss.app.shifak.data.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mhss.app.shifak.util.PrefsConstants
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Module
@ComponentScan("com.mhss.app.shifak.data")
class DataModule {

    @Single
    @Named("ioDispatcher")
    fun getIoDispatcher(): CoroutineContext = Dispatchers.IO

    @Single
    @Named("defaultDispatcher")
    fun getDefaultDispatcher(): CoroutineContext = Dispatchers.Default

    @Single
    fun getHttpClient() = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor", message)
                }
            }
            level = LogLevel.BODY
        }
    }

    @Single
    fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "encrypted_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Single
    fun getDataStorePreferences(context: Context) = context.dataStore
}

private val Context.dataStore by preferencesDataStore(PrefsConstants.DATASTORE_NAME)

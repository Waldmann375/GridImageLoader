package com.example.elinexttestwaldmann

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.request.CachePolicy
import coil.size.Precision
import coil.util.CoilUtils
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.Policy

class ElinextTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val imageLoader = ImageLoader.Builder(this)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                    .build()
            }
            //.memoryCachePolicy(CachePolicy.ENABLED)
            //.availableMemoryPercentage(1.0)
            .build()
        Coil.setImageLoader(imageLoader)
    }
}
package com.iserveu.demosupabase.di

import com.iserveu.demosupabase.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {


        return HttpClient(Android) {
            install(WebSockets)
            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json)
                json(contentType = ContentType.Application.FormUrlEncoded)
            }
            install(HttpTimeout){
                socketTimeoutMillis = 60000
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                contentType(ContentType.Application.FormUrlEncoded)
                accept(ContentType.Application.Json)
//                header("apikey", "sbp_a0776b33c2b557641048bc20c8a817cc2aa69098")
//                header("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzemRpaWN6Y296YXdkand1bm5xIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODc4NDM5MDgsImV4cCI6MjAwMzQxOTkwOH0.dtXBm3XVF2hmNSHNjJI6IikYA44QcqYSNtLVSqgxf0Y")
                header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzemRpaWN6Y296YXdkand1bm5xIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODc4NDM5MDgsImV4cCI6MjAwMzQxOTkwOH0.dtXBm3XVF2hmNSHNjJI6IikYA44QcqYSNtLVSqgxf0Y")
            }
            install(Logging){
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun provideIIRCTCApi(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://wszdiiczcozawdjwunnq.supabase.co/storage/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                chain.request().newBuilder()
                    .build()
                    .let(chain::proceed)
            }
            .retryOnConnectionFailure(true)
            .build()
    }

}
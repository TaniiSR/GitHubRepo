package com.task.githubrepo.data.remote

import com.task.githubrepo.BuildConfig
import com.task.githubrepo.data.dtos.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RepoService {
    suspend fun getTrendingRepos(): Response<Repository>
    companion object {
        private const val BASE_URL = "https://api.github.com/"
        fun createService(): RepoService {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RepoService::class.java)
        }
    }
}

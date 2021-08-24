package ru.avtoropova.tetahomework1.model.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.avtoropova.tetahomework1.utils.API_KEY

fun Retrofit.Builder.setClient() = apply {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
        .addHeaderInterceptor()
        .addInterceptor(loggingInterceptor)
        .build()

    this.client(okHttpClient)
}

private fun OkHttpClient.Builder.addHeaderInterceptor() = apply {
    val interceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(request)
    }

    this.addInterceptor(interceptor)

}

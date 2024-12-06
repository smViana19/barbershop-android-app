package br.com.samuel.barbershopapplication.di

import br.com.samuel.barbershopapplication.backendservices.api.ApiAuthService
import br.com.samuel.barbershopapplication.backendservices.api.ApiUserService
import br.com.samuel.barbershopapplication.constants.APP_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn
object ApplicationModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APP_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserApiService(retrofit: Retrofit): ApiUserService {
        return retrofit.create(ApiUserService::class.java)
    }

    @Provides
    fun provideAuthApiService(retrofit: Retrofit): ApiAuthService {
        return retrofit.create(ApiAuthService::class.java)
    }


}
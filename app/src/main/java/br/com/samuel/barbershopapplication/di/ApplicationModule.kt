package br.com.samuel.barbershopapplication.di

import android.content.Context
import br.com.samuel.barbershopapplication.backendservices.api.ApiAppointmentService
import br.com.samuel.barbershopapplication.backendservices.api.ApiAuthService
import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
import br.com.samuel.barbershopapplication.backendservices.api.ApiProfessionalService
import br.com.samuel.barbershopapplication.backendservices.api.ApiServiceService
import br.com.samuel.barbershopapplication.backendservices.api.ApiSpecialtyService
import br.com.samuel.barbershopapplication.backendservices.api.ApiUserService
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsServiceImpl
import br.com.samuel.barbershopapplication.constants.APP_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideSharedPrefsService(@ApplicationContext context: Context): SharedPrefsService {
        return SharedPrefsServiceImpl(context)
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): ApiUserService {
        return retrofit.create(ApiUserService::class.java)
    }

    @Provides
    fun provideAuthApiService(retrofit: Retrofit): ApiAuthService {
        return retrofit.create(ApiAuthService::class.java)
    }

    @Provides
    fun provideProfessionalApiService(retrofit: Retrofit): ApiProfessionalService {
        return retrofit.create(ApiProfessionalService::class.java)
    }

    @Provides
    fun provideSpecialtyApiService(retrofit: Retrofit): ApiSpecialtyService {
        return retrofit.create(ApiSpecialtyService::class.java)
    }

    @Provides
    fun provideServiceService(retrofit: Retrofit): ApiServiceService {
        return retrofit.create(ApiServiceService::class.java)
    }

    @Provides
    fun provideAppointmentService(retrofit: Retrofit): ApiAppointmentService {
        return retrofit.create(ApiAppointmentService::class.java)
    }

    @Provides
    fun provideAvailabilityService(retrofit: Retrofit): ApiAvailabilityService {
        return retrofit.create(ApiAvailabilityService::class.java)
    }


}
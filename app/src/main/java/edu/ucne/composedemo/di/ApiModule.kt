package edu.ucne.composedemo.di


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.composedemo.data.remote.TicketingApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    const val BASE_URL = "https://sistematicket.azurewebsites.net/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateAdapter())
            .build()

    @Provides
    @Singleton
    fun provideTicketingApi(moshi: Moshi): TicketingApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketingApi::class.java)
    }

    const val BASE_URL2 = "https://anydesklog.azurewebsites.net/"
    @Provides
    @Singleton
    fun provideAnyDeskLogApi(moshi: Moshi): TicketingApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketingApi::class.java)
    }

}
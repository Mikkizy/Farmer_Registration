package com.ukaka.farmerregistration.dependency_injection

import android.app.Application
import androidx.room.Room
import com.ukaka.farmerregistration.data.FarmersDatabase
import com.ukaka.farmerregistration.data.repository.FarmersRepositoryImpl
import com.ukaka.farmerregistration.domain.repository.FarmersRepository
import com.ukaka.farmerregistration.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): FarmersDatabase =
        Room.databaseBuilder(
            app,
            FarmersDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideFarmersRepository(database: FarmersDatabase): FarmersRepository =
        FarmersRepositoryImpl(database.farmerDao)

}
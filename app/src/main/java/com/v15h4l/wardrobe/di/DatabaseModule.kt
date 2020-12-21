package com.v15h4l.wardrobe.di

import android.content.Context
import androidx.room.Room
import com.v15h4l.wardrobe.data.room.AppDatabase
import com.v15h4l.wardrobe.data.room.ClothDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideClothDao(appDatabase: AppDatabase): ClothDao = appDatabase.clothDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    companion object {
        const val DATABASE_NAME = "ClothDatabase"
    }

}
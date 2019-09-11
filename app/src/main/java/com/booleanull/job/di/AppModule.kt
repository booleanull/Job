package com.booleanull.job.di

import android.content.Context
import com.booleanull.job.domain.JobRepository
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun provideContext() = context

    @Singleton
    @Provides
    internal fun provideCicerone() = Cicerone.create()

    @Singleton
    @Provides
    internal fun provideJobRepository(): JobRepository {
        return JobRepository()
    }
}
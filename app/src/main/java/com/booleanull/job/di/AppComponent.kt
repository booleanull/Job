package com.booleanull.job.di

import com.booleanull.job.domain.JobRepository
import com.booleanull.job.domain.JobSource
import com.booleanull.job.ui.MainActivity
import com.booleanull.job.ui.job.JobFragment
import com.booleanull.job.ui.job.JobViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(jobViewModel: JobViewModel)
    fun inject(jobFragment: JobFragment)

    fun inject(jobRepository: JobRepository)
    fun inject(jobSource: JobSource)
}
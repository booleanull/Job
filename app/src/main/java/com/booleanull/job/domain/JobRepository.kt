package com.booleanull.job.domain

import com.booleanull.job.MyApplication
import com.booleanull.job.data.ApiService
import com.booleanull.job.domain.models.Job
import io.reactivex.Single
import javax.inject.Inject

class JobRepository {

    @Inject
    lateinit var service: ApiService

    init {
        MyApplication.appComponent.inject(this)
    }

    fun provideJobs(search: String, page: Int): Single<List<Job>> =
        service.getSearchJobAsync(search, page).map { it.map { job -> job.toJob() } }
}
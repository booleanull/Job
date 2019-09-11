package com.booleanull.job.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.booleanull.job.domain.models.Job
import io.reactivex.disposables.CompositeDisposable

class JobSourceFactory(
    private val jobRepository: JobRepository,
    private val query: String,
    private val errorLiveData: MutableLiveData<Boolean>,
    private val composibleDisposable: CompositeDisposable
) : DataSource.Factory<Int, Job>() {

    override fun create(): DataSource<Int, Job> {
        return JobSource(jobRepository, query, errorLiveData, composibleDisposable)
    }
}
package com.booleanull.job.domain

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.booleanull.job.domain.models.Job

class JobSource(private val jobRepository: JobRepository, private val query: String) : PageKeyedDataSource<Int, Job>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Job>) {
        jobRepository.provideJobs(query, 0).subscribe { response ->
            callback.onResult(response, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
        jobRepository.provideJobs(query, params.key).subscribe { response ->
            callback.onResult(response,  params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
        jobRepository.provideJobs(query, params.key).subscribe { response ->
            callback.onResult(response,  params.key.dec())
        }
    }
}
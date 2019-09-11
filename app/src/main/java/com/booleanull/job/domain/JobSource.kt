package com.booleanull.job.domain

import androidx.paging.PageKeyedDataSource
import com.booleanull.job.domain.models.Job

class JobSource(private val jobRepository: JobRepository, private val query: String) : PageKeyedDataSource<Int, Job>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Job>) {
        callback.onResult(listOf(Job("", "", "", "", "COMPANY", null, "dkjasldjaslkd", "Sdasdasd", null, null, null)), null, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
    }
}
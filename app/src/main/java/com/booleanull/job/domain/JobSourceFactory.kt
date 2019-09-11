package com.booleanull.job.domain

import androidx.paging.DataSource
import com.booleanull.job.domain.models.Job

class JobSourceFactory(
    private val jobRepository: JobRepository,
    private val query: String
) : DataSource.Factory<Int, Job>() {

    override fun create(): DataSource<Int, Job> {
        return JobSource(jobRepository, query)
    }
}
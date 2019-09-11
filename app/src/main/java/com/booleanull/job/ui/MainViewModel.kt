package com.booleanull.job.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.booleanull.job.domain.JobRepository
import com.booleanull.job.domain.JobSourceFactory
import com.booleanull.job.domain.models.Job

class MainViewModel : ViewModel() {

    private var liveData: LiveData<PagedList<Job>>? = null

    fun getJobs(jobRepository: JobRepository, query: String) : LiveData<PagedList<Job>> {
        if (liveData == null) {
            val factory = JobSourceFactory(jobRepository, query)
            val config = PagedList.Config.Builder()
                .setPageSize(50)
                .setInitialLoadSizeHint(50)
                .setEnablePlaceholders(false)
                .build()

            liveData = LivePagedListBuilder<Int, Job>(factory, config)
                .setInitialLoadKey(1)
                .build()
        }
        return liveData as LiveData<PagedList<Job>>
    }
}
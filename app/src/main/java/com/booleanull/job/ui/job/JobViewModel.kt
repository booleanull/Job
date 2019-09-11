package com.booleanull.job.ui.job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.booleanull.job.MyApplication
import com.booleanull.job.domain.JobRepository
import com.booleanull.job.domain.JobSourceFactory
import com.booleanull.job.domain.models.Job
import javax.inject.Inject

class JobViewModel : ViewModel() {

    @Inject
    lateinit var jobRepository: JobRepository

    private var liveData: LiveData<PagedList<Job>>? = null

    val foundLiveData = MutableLiveData<Boolean>()

    init {
        MyApplication.appComponent.inject(this)
    }

    fun getJobs(jobRepository: JobRepository, query: String): LiveData<PagedList<Job>> {
        val factory = JobSourceFactory(jobRepository, query)
        val config = PagedList.Config.Builder()
            .setPageSize(50)
            .setInitialLoadSizeHint(50)
            .setEnablePlaceholders(false)
            .build()

        liveData = LivePagedListBuilder<Int, Job>(factory, config)
            .setInitialLoadKey(1)
            .build()

        return liveData as LiveData<PagedList<Job>>
    }
}
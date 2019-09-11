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
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class JobViewModel : ViewModel() {

    var liveData: LiveData<PagedList<Job>>? = null

    val foundLiveData = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    init {
        MyApplication.appComponent.inject(this)
    }

    fun getJobs(jobRepository: JobRepository, query: String): LiveData<PagedList<Job>> {
        val factory = JobSourceFactory(jobRepository, query, foundLiveData, errorLiveData, compositeDisposable)
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
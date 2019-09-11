package com.booleanull.job.domain

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.booleanull.job.MyApplication
import com.booleanull.job.domain.models.Job
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class JobSource(
    private val jobRepository: JobRepository,
    private val query: String,
    private val foundLiveData: MutableLiveData<Boolean>,
    private val errorLiveData: MutableLiveData<Boolean>,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Job>() {

    private fun onError(t: Throwable) {
        errorLiveData.value = true
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Job>) {
        compositeDisposable.add(jobRepository.provideJobs(query, 0)
            .doOnError {
                onError(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.onResult(response, null, 1)
                foundLiveData.value = response.isEmpty()
            }, { onError(it) })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
        compositeDisposable.add(jobRepository.provideJobs(query, params.key)
            .doOnError {
                onError(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.onResult(response, params.key.inc())
            }, { onError(it) })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
        compositeDisposable.add(jobRepository.provideJobs(query, params.key)
            .doOnError {
                onError(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.onResult(response, params.key.dec())
            }, { onError(it) })
        )
    }
}
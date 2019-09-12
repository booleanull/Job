package com.booleanull.job.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.booleanull.job.domain.models.Job
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

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

    private fun loadData(onSuccess: (list: List<Job>) -> Unit) {
        compositeDisposable.add(jobRepository.provideJobs(query, 0)
            .doOnError {
                onError(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                onSuccess(result)
            }, { onError(it) })
        )
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Job>
    ) {
        loadData {
            callback.onResult(it, null, 1)
            foundLiveData.value = it.isEmpty()
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
        loadData {
            callback.onResult(it, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Job>) {
        loadData {
            callback.onResult(it, params.key.dec())
        }
    }
}
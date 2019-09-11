package com.booleanull.job.data

import com.booleanull.job.data.models.JobEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/positions.json")
    fun getSearchJobAsync(@Query("search") search: String, @Query("page") page: Int): Single<List<JobEntity>>
}
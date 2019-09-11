package com.booleanull.job.data.models

import com.booleanull.job.domain.models.Job
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JobEntity {

    @Expose
    var id = ""
    @Expose
    var type = ""
    @Expose
    var url = ""
    @SerializedName("created_at")
    var created = ""
    @Expose
    var company = ""
    @SerializedName("company_url")
    var companyUrl = ""
    @Expose
    var location = ""
    @Expose
    var title = ""
    @Expose
    var description = ""
    @SerializedName("how_to_apply")
    var apply = ""
    @SerializedName("company_logo")
    var companyLogo = ""

    fun toJob() = Job(
        id,
        type,
        url,
        created,
        company,
        companyUrl,
        location,
        title,
        description,
        apply,
        companyLogo
    )
}
package com.booleanull.job.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Job(
    val id: String?,
    val type: String?,
    val url: String?,
    val created: String?,
    val company: String?,
    val companyUrl: String?,
    val location: String?,
    val title: String?,
    val description: String?,
    val apply: String?,
    val companyLogo: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(created)
        parcel.writeString(company)
        parcel.writeString(companyUrl)
        parcel.writeString(location)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(apply)
        parcel.writeString(companyLogo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Job> {
        override fun createFromParcel(parcel: Parcel): Job {
            return Job(parcel)
        }

        override fun newArray(size: Int): Array<Job?> {
            return arrayOfNulls(size)
        }
    }
}
package com.booleanull.job.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.booleanull.job.domain.models.Job
import com.booleanull.job.ui.job.JobFragment
import com.booleanull.job.ui.job_about.JobAboutFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class JobScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return JobFragment()
        }
    }

    class JobAboutScreen(private val job: Job) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val fragment = JobAboutFragment()
            val bundle = Bundle()
            bundle.putParcelable("item", job)
            fragment.arguments = bundle
            return fragment
        }
    }
}
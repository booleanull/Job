package com.booleanull.job.ui.job_about

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.booleanull.job.R
import com.booleanull.job.domain.models.Job
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about_job.*
import org.jsoup.Jsoup

class JobAboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.get("position")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            image_logo.transitionName = "position$position"
        }
        val job = arguments?.get("item") as Job

        activity?.toolbar?.title = job.title
        activity?.toolbar?.navigationIcon =
            ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back_black_24dp)
        activity?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        text_company.text = job.company
        text_description.text = Jsoup.parse(job.description).text()
        text_created.text = job.created
        text_type.text = job.type
        text_location.text = job.location

        Picasso.with(context)
            .load(job.companyLogo)
            .into(image_logo)

        view_company.setOnClickListener {
            job.companyUrl?.let {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
            }
        }

        button_more.setOnClickListener {
            job.url?.let {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
            }
        }
    }
}
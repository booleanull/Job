package com.booleanull.job.ui.job

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.job.R
import com.booleanull.job.domain.models.Job
import com.booleanull.job.ui.MainActivity
import com.booleanull.job.ui.job.JobAdapter.JobHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_job.view.*

class JobAdapter : PagedListAdapter<Job, JobHolder>(getDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JobHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_job, parent, false))

    override fun onBindViewHolder(holder: JobHolder, position: Int) = holder.bind(getItem(position)!!)


    inner class JobHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(job: Job) {
            with(itemView) {
                text_title.text = job.title
                text_city.text = job.location
                Picasso.with(context)
                    .load(job.companyLogo)
                    .into(image_logo)

                item.setOnClickListener {
                    (context as MainActivity).navigateToDetail(job)
                }
            }
        }
    }


    companion object {
        fun getDiff() = object : DiffUtil.ItemCallback<Job>() {
            override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
                return oldItem.id.equals(newItem.id)
            }

            override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
                return oldItem == newItem
            }
        }
    }
}

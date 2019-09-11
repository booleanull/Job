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
import com.booleanull.job.ui.job.JobAdapter.JobHolder

class JobAdapter : PagedListAdapter<Job, JobHolder>(getDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobHolder {
        val inflater = LayoutInflater.from(parent.context)
        return JobHolder(inflater.inflate(R.layout.view_job, null))
    }

    override fun onBindViewHolder(holder: JobHolder, position: Int) {
        val job: Job = getItem(position) ?: return

        holder.textCity?.text = job.location
    }

    class JobHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textCity: TextView? = null

        init {
            textCity = itemView.findViewById(R.id.text_city)
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

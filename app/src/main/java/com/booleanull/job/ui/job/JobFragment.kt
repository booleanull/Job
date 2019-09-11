package com.booleanull.job.ui.job

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.job.MyApplication
import com.booleanull.job.R
import com.booleanull.job.domain.JobRepository
import com.booleanull.job.domain.models.Job
import com.booleanull.job.utils.Line
import com.booleanull.job.utils.MyQueryTextListener
import com.booleanull.job.utils.RecyclerDivider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_job.*
import javax.inject.Inject

class JobFragment : Fragment() {

    @Inject
    lateinit var jobRepository: JobRepository

    private lateinit var jobViewModel: JobViewModel
    private lateinit var jobAdapter: JobAdapter

    init {
        MyApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        jobViewModel = ViewModelProviders.of(this).get(JobViewModel::class.java)
        return inflater.inflate(R.layout.fragment_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.toolbar?.title = getString(R.string.app_name)
        activity?.toolbar?.navigationIcon = null

        jobAdapter = JobAdapter()
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.addItemDecoration(
            RecyclerDivider(
                1,
                Line(0, 0, 1, ContextCompat.getColor(context!!, R.color.colorItemLine))
            )
        )
        recycler.adapter = jobAdapter

        jobViewModel.foundLiveData.observe(this, Observer {
            text_start.visibility = View.GONE
            if (!it) {
                text_found.visibility = View.GONE
                recycler.visibility = View.VISIBLE
            } else {
                text_found.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            }
        })

        jobAdapter.submitList(null)
        jobViewModel.liveData?.observe(this, Observer {
            jobAdapter.submitList(it)
        })

        jobViewModel.errorLiveData.observe(this, Observer {
            if(it) {
                Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                jobViewModel.errorLiveData.value = false
            }
        })
    }

    private fun getJobsList(string: String) {
        progress.visibility = View.VISIBLE
        jobViewModel.foundLiveData.value = false

        jobViewModel.getJobs(jobRepository, string).observe(this, Observer {
            jobAdapter.submitList(it)
            progress.visibility = View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(MyQueryTextListener {
            getJobsList(it)
        })
    }
}
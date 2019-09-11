package com.booleanull.job.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.job.R
import com.booleanull.job.ui.job.JobAdapter
import com.booleanull.job.utils.Line
import com.booleanull.job.utils.RecyclerDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jobAdapter = JobAdapter()
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.addItemDecoration(
            RecyclerDivider(
                1,
                Line(0, 0, 1, ContextCompat.getColor(this, R.color.colorItemLine))
            )
        )
    }
}

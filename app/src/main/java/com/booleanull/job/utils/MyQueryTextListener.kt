package com.booleanull.job.utils

import androidx.appcompat.widget.SearchView

    class MyQueryTextListener(private val submit: (query: String) -> Unit): SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        submit.invoke(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
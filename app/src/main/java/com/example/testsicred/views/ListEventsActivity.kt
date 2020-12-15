package com.example.testsicred.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.viewmodels.ListEventViewModel
import com.example.testsicred.R
import com.example.testsicred.extensions.createsErrorDialog
import com.example.testsicred.extensions.createsLoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEventsActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListEventViewModel>()
    private lateinit var alertLoading : AlertDialog

    private val listEventsAdapter: ListEventsAdapter by lazy {
        ListEventsAdapter(object : ListEventsAdapter.ListEventsListener {
            override fun seeDetail(id: String) {
                val intent = Intent(this@ListEventsActivity, DetailEventActivity::class.java)
                intent.putExtra(EVENT_ID, id)
                startActivity(intent)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_events)
        initViews()
        inscribeObservers()
        viewModel.getListEvents()
    }

    private fun initViews(){
        alertLoading = createsLoadingDialog()
        val eventListRecyclerView: RecyclerView = findViewById(R.id.recycler_view_event_listing)
        eventListRecyclerView.setHasFixedSize(true)
        eventListRecyclerView.adapter = listEventsAdapter
    }

    private fun inscribeObservers(){
        viewModel.error.observe(this, {
            alertLoading.dismiss()
            createsErrorDialog(R.string.txt_something_went_wrong)
        })

        viewModel.noNetworking.observe(this, {
            alertLoading.dismiss()
            createsErrorDialog(R.string.txt_no_internet)
        })

        viewModel.listOfEvents.observe(this,{
            alertLoading.dismiss()
            listEventsAdapter.listOfEvents = it
        })
    }

    companion object{
        const val EVENT_ID = "event_id"
    }

}
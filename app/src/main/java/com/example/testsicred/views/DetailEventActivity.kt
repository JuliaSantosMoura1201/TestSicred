package com.example.testsicred.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.presentation.viewmodels.DetailEventViewModel
import com.example.testsicred.R
import com.example.testsicred.databinding.ActivityDetailEventBinding
import com.example.testsicred.extensions.createsErrorDialog
import com.example.testsicred.extensions.createsLoadingDialog
import com.example.testsicred.views.ListEventsActivity.Companion.EVENT_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailEventActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetailEventViewModel>()
    private lateinit var viewDataBinding: ActivityDetailEventBinding
    private lateinit var alertLoading : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_event)
        initViews()
        inscribeObservers()
        viewModel.getEvent(intent.getStringExtra(EVENT_ID))
    }

    private fun initViews(){
        alertLoading = createsLoadingDialog()
        viewDataBinding.shareListener =
        View.OnClickListener { viewModel.shareContent() }
    }
    private fun inscribeObservers() {
        viewModel.error.observe(this, {
            alertLoading.dismiss()
            createsErrorDialog(R.string.txt_something_went_wrong)
        })

        viewModel.noNetworking.observe(this, {
            alertLoading.dismiss()
            createsErrorDialog(R.string.txt_no_internet)
        })

        viewModel.event.observe(this, { event ->
            alertLoading.dismiss()
            viewDataBinding.eventItem = event
            viewDataBinding.checkInListener =
                View.OnClickListener {goToCheckInActivity(event.id)}
        })

        viewModel.contentToBeShare.observe(this, {
            shareContent(it)
        })
    }

    private fun goToCheckInActivity(id: String){
        val intent = Intent(this@DetailEventActivity, CheckInActivity::class.java)
        intent.putExtra(EVENT_ID, id)
        startActivity(intent)
    }

    private fun shareContent(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }
}
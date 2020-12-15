package com.example.testsicred.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testsicred.R
import kotlinx.android.synthetic.main.dialog_error.*

fun AppCompatActivity.createsGenericDialog(layout: Int): AlertDialog {
    val dialogView: View = LayoutInflater.from(this)
        .inflate(layout, window?.decorView as ViewGroup, false)
    val alert: AlertDialog = AlertDialog.Builder(this)
        .setView(dialogView)
        .show()
    alert.setCanceledOnTouchOutside(false)
    return alert
}

fun AppCompatActivity.createsErrorDialog(textId: Int): AlertDialog {
    val alert = createsGenericDialog(R.layout.dialog_error)
    alert.txt_error_message.text = getString(textId)
    alert.img_btn_close.setOnClickListener { alert.dismiss() }
    alert.btn_close.setOnClickListener { alert.dismiss() }
    return alert
}

fun AppCompatActivity.createsLoadingDialog(): AlertDialog {
    val alert = createsGenericDialog(R.layout.dialog_loading)
    alert.setBackgroundTransparency()
    return alert
}

package com.techbots.latestnews.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ActivityGenerateDogsBinding
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.viewmodel.ImageGenerateVM

class ImageGenerateActivity : AppCompatActivity(), ImageGenerateVM.CallBacks{
    private lateinit var dataBinding: ActivityGenerateDogsBinding
    private lateinit var viewModel: ImageGenerateVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_generate_dogs
        )
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ImageGenerateVM::class.java)
        dataBinding.viewModel = viewModel
        viewModel.setCallBacks(this)
        dataBinding.button.setOnClickListener(View.OnClickListener {
            viewModel.getImage()
        })
    }

    override fun networkError() {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("No network found")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(getString(R.string.network_error))
        // show alert dialog
        alert.show()
    }
}

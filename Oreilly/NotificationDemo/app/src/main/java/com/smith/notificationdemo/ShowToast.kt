package com.smith.notificationdemo

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast

class ShowToast: View.OnClickListener {
    override fun onClick(view: View?) {
        Toast.makeText(view?.context, "Toast created from snackbar action", Toast.LENGTH_LONG).show()
    }
}
package com.tolgakurucay.shoppingappforinterview.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.tolgakurucay.shoppingappforinterview.R

object Extensions {


    fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            this.strokeWidth = 10f
            this.arrowEnabled = true
            this.centerRadius = 45f
            this.arrowScale =60f
            this.backgroundColor= android.R.color.holo_orange_dark
            start()
        }
    }

    fun ImageView.downloadFromUri(uri: Uri?){
        Glide.with(context)
            .load(uri)
            .placeholder(placeHolderProgressBar(context))
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("android:imageFromURI")
    fun imageFromURI(imageView : ImageView,uriString : String){
        val uri = uriString.toUri()
        imageView.downloadFromUri(uri)
    }

    fun Activity.showShortToast(message : String) = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    fun Activity.showLongToast(message : String) = Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    fun Fragment.showShortToast(message : String) = Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    fun Fragment.showLongToast(message : String) = Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()

    fun Activity.showOneActionAlert(title : String,message : String,okayButtonAction : ()->Unit){
        return AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(title)
            .setIcon(R.drawable.info_image_black)
            .setMessage(message)
            .setPositiveButton(getString(R.string.okay)) { _, _ ->
                okayButtonAction()
            }
            .create()
            .show()
    }

    fun Fragment.showOneActionAlert(title : String,message : String,okayButtonAction : ()->Unit){
        return AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(title)
            .setIcon(R.drawable.info_image_black)
            .setMessage(message)
            .setPositiveButton(getString(R.string.okay)) { _, _ ->
                okayButtonAction()
            }
            .create()
            .show()
    }

    fun Fragment.showOneActionAlertRetry(title : String,message : String,okayButtonAction : ()->Unit){
        return AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(title)
            .setIcon(R.drawable.info_image_black)
            .setMessage(message)
            .setPositiveButton(getString(R.string.okay)) { _, _ ->
                okayButtonAction()
            }
            .create()
            .show()
    }

}
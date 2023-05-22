package com.example.pillativecareapp.util

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.example.pillativecareapp.R
import com.example.pillativecareapp.data.Status
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.ui.base.BaseAdapter
import com.google.android.material.imageview.ShapeableImageView

//region recycler
@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}
//endregion

//region visibility
@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> View.showWhenSuccess(status: Status<T>?) {
    val transition = Fade()
    TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
    visibility = if (status is Status.Success) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenFailure"])
fun <T> View.showWhenFailure(status: Status<T>?) {
    val transition = Fade()
    TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
    visibility = if (status is Status.Failure) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> View.showWhenLoading(status: Status<T>?) {
    val transition = Fade()
    TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
    visibility = if (status is Status.Loading) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("app:isVisible")
fun View.isVisible(isVisible: Boolean?) {
    visibility = if (isVisible == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
//endregion


@BindingAdapter("imageUrl")
fun ShapeableImageView.loadImage(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
//        .placeholder(R.raw.loading)
//        .error(R.raw.error)
        .into(this)
}

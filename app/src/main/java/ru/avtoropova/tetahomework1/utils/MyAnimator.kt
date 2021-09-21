package ru.avtoropova.tetahomework1.utils

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.R

class MyAnimator: DefaultItemAnimator() {
    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        if(holder!=null)
            holder.itemView.animation=AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        return super.animateAdd(holder)
    }
}
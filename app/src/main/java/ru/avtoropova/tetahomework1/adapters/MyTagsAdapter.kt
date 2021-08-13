package ru.avtoropova.tetahomework1.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.model.dto.TagDto
import java.util.ArrayList

class MyTagsAdapter(
    private val mListener: (TagDto) -> Unit
) : RecyclerView.Adapter<MyTagsAdapter.MyTagsViewHolder>() {
    var data: MutableList<TagDto> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagsViewHolder {
        return MyTagsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyTagsViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { item.let { mListener.invoke(it) } }

    }

    override fun getItemCount(): Int = data.size

    fun initData(tags: List<TagDto>?) {
        if (tags != null) {
            data.clear()
            data.addAll(tags)
            notifyDataSetChanged()
            Log.d("initDataBlock", "size  = $itemCount")
        }
    }

    class MyTagsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tagName: TextView = view.findViewById(R.id.tvTag)
        fun bind(tag: TagDto) {
            tagName.text = tag.tag
        }
    }
}
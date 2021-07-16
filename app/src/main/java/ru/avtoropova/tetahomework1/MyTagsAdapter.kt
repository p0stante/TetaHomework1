package ru.avtoropova.tetahomework1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.data.dto.MovieDto
import ru.avtoropova.tetahomework1.data.dto.TagDto

class MyTagsAdapter(
    context: Context,
    private val tags: List<TagDto>,
    private val mListener: (TagDto) -> Unit
) : RecyclerView.Adapter<MyTagsAdapter.MyTagsViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    class MyTagsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tagName: TextView = view.findViewById(R.id.tvTag)
        fun bind(tag: TagDto) {
            tagName.text = tag.tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagsViewHolder {
        return MyTagsViewHolder(inflater.inflate(R.layout.item_tag, parent, false))
    }

    override fun onBindViewHolder(holder: MyTagsViewHolder, position: Int) {
        val item = tags[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { item?.let { it -> mListener.invoke(it) } }

    }

    override fun getItemCount(): Int = tags.size
}
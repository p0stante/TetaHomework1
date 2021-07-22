package ru.avtoropova.tetahomework1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.data.dto.ActorDto

class MyActorsAdapter(private val actors: ArrayList<ActorDto>) :
    RecyclerView.Adapter<MyActorsAdapter.MyActorsViewHolder>() {
    class MyActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.tv_actor)
        private val image: ImageView = view.findViewById(R.id.iv_actor)
        fun bind(actor: ActorDto) {
            name.text = actor.name
            image.load(actor.imageUrl)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyActorsViewHolder {
        return MyActorsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyActorsViewHolder, position: Int) {
        val item = actors[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = actors.size
}
package ru.avtoropova.tetahomework1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.model.room.entities.Movie

class MyMoviesAdapter(private val mListener: (Movie) -> Unit) :
    ListAdapter<Movie, MyMovieViewHolder>(MoviesCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder {
        return MyMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { item?.let { it -> mListener.invoke(it) } }
    }
}
package ru.avtoropova.tetahomework1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.avtoropova.tetahomework1.data.dto.MovieDto

class MyMoviesAdapter(context: Context, private val mListener: (MovieDto) -> Unit) :
    ListAdapter<MovieDto, MyMovieViewHolder>(MoviesCallback()) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder {
        return MyMovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { item?.let { it -> mListener.invoke(it) } }
    }

}
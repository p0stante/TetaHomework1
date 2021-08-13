package ru.avtoropova.tetahomework1.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.avtoropova.tetahomework1.model.room.entities.Movie

class MoviesCallback :
    DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title

    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
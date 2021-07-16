package ru.avtoropova.tetahomework1

import android.graphics.Movie
import androidx.recyclerview.widget.DiffUtil
import ru.avtoropova.tetahomework1.data.dto.MovieDto

class MoviesCallback() :
    DiffUtil.ItemCallback<MovieDto>() {
    override fun areItemsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
        return oldItem.title == newItem.title

    }

    override fun areContentsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
        return oldItem == newItem
    }

}
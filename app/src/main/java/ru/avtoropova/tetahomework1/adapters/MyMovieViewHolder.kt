package ru.avtoropova.tetahomework1.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.model.room.entities.Movie

class MyMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.ivHeader)
    private val title: TextView = view.findViewById(R.id.tvTitle)
    private val description: TextView = view.findViewById(R.id.tvDescription)
    private val ageRestriction: TextView = view.findViewById(R.id.tvAgeRestriction)
    private val star1: ImageView = view.findViewById(R.id.ivStar1)
    private val star2: ImageView = view.findViewById(R.id.ivStar2)
    private val star3: ImageView = view.findViewById(R.id.ivStar3)
    private val star4: ImageView = view.findViewById(R.id.ivStar4)
    private val star5: ImageView = view.findViewById(R.id.ivStar5)

    fun bind(movie: Movie) {
        poster.load(movie.imageUrl)
        title.text = movie.title
        description.text = movie.description
        ageRestriction.text = movie.ageRestriction.toString() + "+"
        when (movie.rateScore) {
            1 -> {
                star1.setImageResource(R.drawable.ic_star_black)
                star2.setImageResource(R.drawable.ic_star_white)
                star3.setImageResource(R.drawable.ic_star_white)
                star4.setImageResource(R.drawable.ic_star_white)
                star5.setImageResource(R.drawable.ic_star_white)
            }
            2 -> {
                star1.setImageResource(R.drawable.ic_star_black)
                star2.setImageResource(R.drawable.ic_star_black)
                star3.setImageResource(R.drawable.ic_star_white)
                star4.setImageResource(R.drawable.ic_star_white)
                star5.setImageResource(R.drawable.ic_star_white)
            }
            3 -> {
                star1.setImageResource(R.drawable.ic_star_black)
                star2.setImageResource(R.drawable.ic_star_black)
                star3.setImageResource(R.drawable.ic_star_black)
                star4.setImageResource(R.drawable.ic_star_white)
                star5.setImageResource(R.drawable.ic_star_white)
            }
            4 -> {
                star1.setImageResource(R.drawable.ic_star_black)
                star2.setImageResource(R.drawable.ic_star_black)
                star3.setImageResource(R.drawable.ic_star_black)
                star4.setImageResource(R.drawable.ic_star_black)
                star5.setImageResource(R.drawable.ic_star_white)
            }
            5 -> {
                star1.setImageResource(R.drawable.ic_star_black)
                star2.setImageResource(R.drawable.ic_star_black)
                star3.setImageResource(R.drawable.ic_star_black)
                star4.setImageResource(R.drawable.ic_star_black)
                star5.setImageResource(R.drawable.ic_star_black)
            }
            else -> {
                star1.setImageResource(R.drawable.ic_star_white)
                star2.setImageResource(R.drawable.ic_star_white)
                star3.setImageResource(R.drawable.ic_star_white)
                star4.setImageResource(R.drawable.ic_star_white)
                star5.setImageResource(R.drawable.ic_star_white)
            }
        }
    }
}
package ru.avtoropova.tetahomework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.adapters.MyActorsAdapter
import ru.avtoropova.tetahomework1.model.room.entities.MoviesWithActors
import ru.avtoropova.tetahomework1.viewmodels.MoviesModel

class MovieDetailsFragment : Fragment() {
    private val moviesModel: MoviesModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movie: MoviesWithActors? = moviesModel.movie.value
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieTitle = view.findViewById<TextView>(R.id.tv_movie_title)
        val moviePoster = view.findViewById<ImageView>(R.id.iv_movie_poster)
        val movieDescription = view.findViewById<TextView>(R.id.tv_movie_description)
        val ageRestriction = view.findViewById<TextView>(R.id.tv_age_restriction)
        val rvActors = view.findViewById<RecyclerView>(R.id.rv_actors)
        val actorsAdapter = movie?.actors?.let { MyActorsAdapter(it) }
        movieTitle.text = movie?.movie?.title
        movieDescription.text = movie?.movie?.description
        moviePoster.load(movie?.movie?.imageUrl)
        ageRestriction.text = movie?.movie?.ageRestriction.toString() + R.string.plus
        rvActors.adapter = actorsAdapter
        rvActors.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        return view
    }

}
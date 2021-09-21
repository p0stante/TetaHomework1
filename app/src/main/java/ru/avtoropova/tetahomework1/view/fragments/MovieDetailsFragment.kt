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
import androidx.transition.TransitionInflater
import coil.load
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.adapters.MyActorsAdapter
import ru.avtoropova.tetahomework1.model.room.entities.Actor
import ru.avtoropova.tetahomework1.model.room.entities.Movie
import ru.avtoropova.tetahomework1.model.room.entities.Tag
import ru.avtoropova.tetahomework1.viewmodels.MoviesModel

class MovieDetailsFragment : Fragment() {
    private val moviesModel: MoviesModel by activityViewModels()
    var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movie= moviesModel.movie.value
        val tags: List<Tag>? = moviesModel.movieTags.value
        val actors: List<Actor>? = moviesModel.actors.value
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieTitle = view.findViewById<TextView>(R.id.tv_movie_title)
        val moviePoster = view.findViewById<ImageView>(R.id.iv_movie_poster)
        val movieDescription = view.findViewById<TextView>(R.id.tv_movie_description)
        val ageRestriction = view.findViewById<TextView>(R.id.tv_age_restriction)
        val date = view.findViewById<TextView>(R.id.tv_date)
        val tag = view.findViewById<TextView>(R.id.tv_movie_tag)
        val rvActors = view.findViewById<RecyclerView>(R.id.rv_actors)
        val actorsAdapter = actors?.let { MyActorsAdapter(it) }
        movieTitle.text = movie?.title
        movieDescription.text = movie?.description
        date.text = movie?.releaseDate
        tag.text = tags?.get(0)?.tagName
        moviePoster.load(movie?.imageUrl)
        ageRestriction.text = movie?.ageRestriction
        rvActors.adapter = actorsAdapter
        rvActors.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.iv_movie_poster).transitionName = "poster_${movie?.movieId}"
    }

}
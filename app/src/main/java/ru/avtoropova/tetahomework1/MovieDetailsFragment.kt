package ru.avtoropova.tetahomework1

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import ru.avtoropova.tetahomework1.data.dto.MovieDto

class MovieDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val context=context
        val movie: MovieDto? = arguments?.getParcelable("MOVIE")
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieTitle = view.findViewById<TextView>(R.id.tv_movie_title)
        val moviePoster = view.findViewById<ImageView>(R.id.iv_movie_poster)
        val movieDescription = view.findViewById<TextView>(R.id.tv_movie_description)
        movieTitle.text = movie?.title
        movieDescription.text = movie?.description
        moviePoster.load(movie?.imageUrl)
        return view
    }

    companion object {
        fun newInstance(movie: MovieDto): MovieDetailsFragment {
            val args = Bundle()
            args.putParcelable("MOVIE", movie)
            val fragment = MovieDetailsFragment()
            fragment.setArguments(args)
            return fragment

        }
    }
}
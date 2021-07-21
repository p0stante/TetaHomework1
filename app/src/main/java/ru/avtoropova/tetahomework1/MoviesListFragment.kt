package ru.avtoropova.tetahomework1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.features.movies.MoviesDataSourceImpl
import ru.avtoropova.tetahomework1.features.tags.TagsDataSourceImpl

class MoviesListFragment : Fragment() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var tagsModel: TagsModel

    private var moviesListFragmentListener: MoviesListFragmentListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context
        initDataSource()
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val rvMovies = view.findViewById<RecyclerView>(R.id.rv_movies)
        val rvTags = view.findViewById<RecyclerView>(R.id.rv_tags)
        val movies = moviesModel.getMovies()
        val adapter =
            context?.let {
                MyMoviesAdapter(it) {
                    Toast.makeText(
                        context,
                        it.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        if (adapter != null) {
            adapter.submitList(movies)
        }
        rvMovies.adapter = adapter
        rvMovies.layoutManager = GridLayoutManager(context, 2)

        rvMovies.apply {
            setOnClickListener {
                moviesListFragmentListener?.onMovieItemClicked()
            }
        }


        val tags = tagsModel.getTags()
        val tagsAdapter =
            context?.let {
                MyTagsAdapter(it, tags) {
                    Toast.makeText(
                        context,
                        it.tag,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        return view
    }

    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        tagsModel = TagsModel(TagsDataSourceImpl())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesListFragmentListener) {
            moviesListFragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        moviesListFragmentListener = null
    }

    companion object {
        fun newInstance(): MoviesListFragment {
            // val args = Bundle()
            // args.putString("MTS", message)
            val fragment = MoviesListFragment()
            // fragment.arguments = args
            return fragment

        }
    }
}

interface MoviesListFragmentListener {
    fun onMovieItemClicked()
}
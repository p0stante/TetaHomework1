package ru.avtoropova.tetahomework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.avtoropova.tetahomework1.*
import ru.avtoropova.tetahomework1.adapters.MyMoviesAdapter
import ru.avtoropova.tetahomework1.adapters.MyTagsAdapter
import ru.avtoropova.tetahomework1.model.room.entities.Movie
import ru.avtoropova.tetahomework1.utils.MyAnimator
import ru.avtoropova.tetahomework1.viewmodels.MoviesModel
import ru.avtoropova.tetahomework1.viewmodels.TagsModel

class MoviesListFragment : Fragment() {
    private val moviesModel: MoviesModel by activityViewModels()
    private val tagsModel: TagsModel by viewModels()
    private lateinit var rvMovies: RecyclerView
    private lateinit var rvTags: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var adapter: MyMoviesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var tagsAdapter: MyTagsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        rvMovies = view.findViewById(R.id.rv_movies)
        rvTags = view.findViewById(R.id.rv_tags)
        swipeLayout = view.findViewById(R.id.swipe_container)
        progressBar = view.findViewById(R.id.pb_movies)

        adapter = MyMoviesAdapter { movie:Movie,itemView:View->
            val extras = FragmentNavigatorExtras(
                view.findViewById<ImageView>(R.id.ivHeader) to "poster_${movie.movieId}",
            )
            moviesModel.newMovieDetails(movie)
            findNavController().navigate(R.id.action_home_to_movieDetailsFragment,null,null,extras)
        }

        tagsAdapter = MyTagsAdapter {
            Toast.makeText(
                context,
                it.tag,
                Toast.LENGTH_SHORT
            ).show()
        }

        moviesModel.movies.observe(viewLifecycleOwner, Observer(adapter::submitList))
        moviesModel.viewState.observe(viewLifecycleOwner, Observer(::render))

        rvMovies.adapter = adapter
        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.itemAnimator=MyAnimator()
        rvMovies.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        tagsModel.tags.observe(viewLifecycleOwner, Observer(tagsAdapter::initData))
        tagsModel.getTags()

        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        swipeLayout.setOnRefreshListener {
            moviesModel.getNextMovies()
            adapter.notifyDataSetChanged()
            rvMovies.scrollToPosition(0)
            swipeLayout.isRefreshing = false
        }
    }

    data class ViewState(
        val isDownloaded: Boolean = false
    )

    private fun render(viewState: ViewState) = with(viewState) {
        if (isDownloaded) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}


package ru.avtoropova.tetahomework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.avtoropova.tetahomework1.*
import ru.avtoropova.tetahomework1.adapters.MyMoviesAdapter
import ru.avtoropova.tetahomework1.adapters.MyTagsAdapter
import ru.avtoropova.tetahomework1.viewmodels.MoviesModel
import ru.avtoropova.tetahomework1.viewmodels.TagsModel

class MoviesListFragment : Fragment() {
    private lateinit var moviesModel: MoviesModel
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
        moviesModel = ViewModelProvider(requireActivity()).get(MoviesModel::class.java)
        init(view)
        return view
    }

    private fun init(view: View) {
        rvMovies = view.findViewById(R.id.rv_movies)
        rvTags = view.findViewById(R.id.rv_tags)
        swipeLayout = view.findViewById(R.id.swipe_container)
        progressBar = view.findViewById(R.id.pb_movies)

        adapter = MyMoviesAdapter {
            val bundle = bundleOf("MOVIE" to it)
            findNavController().navigate(R.id.action_home_to_movieDetailsFragment, bundle)
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
        //moviesModel.getMovies()
        rvMovies.adapter = adapter
        rvMovies.layoutManager = GridLayoutManager(context, 2)

        tagsModel.tags.observe(viewLifecycleOwner, Observer(tagsAdapter::initData))
        tagsModel.getTags()

        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        swipeLayout.setOnRefreshListener {
            moviesModel.getMoviesShuffle()
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


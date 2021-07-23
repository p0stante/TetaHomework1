package ru.avtoropova.tetahomework1.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.avtoropova.tetahomework1.*
import ru.avtoropova.tetahomework1.adapters.MyMoviesAdapter
import ru.avtoropova.tetahomework1.adapters.MyTagsAdapter
import ru.avtoropova.tetahomework1.data.dto.MovieDto
import ru.avtoropova.tetahomework1.features.movies.MoviesDataSourceImpl
import ru.avtoropova.tetahomework1.features.tags.TagsDataSourceImpl
import ru.avtoropova.tetahomework1.models.MoviesModel
import ru.avtoropova.tetahomework1.models.TagsModel

class MoviesListFragment : Fragment() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var tagsModel: TagsModel

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
        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)

        val adapter = MyMoviesAdapter {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_container, MovieDetailsFragment.newInstance(it))
                ?.addToBackStack(null)?.commit()
        }

        val tags = tagsModel.getTags()
        val tagsAdapter = MyTagsAdapter(tags) {
            Toast.makeText(
                context,
                it.tag,
                Toast.LENGTH_SHORT
            ).show()
        }
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.d(null, "handled $exception")
        }

        viewLifecycleOwner.lifecycleScope.launch(handler) {
            if (adapter != null) {
                moviesModel.loadMovies()
                moviesModel.movies.collectLatest { adapter.submitList(it) }

            }
        }
        rvMovies.adapter = adapter
        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        swipeLayout.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                moviesModel.loadMovies()
                moviesModel.movies.collectLatest {
                    adapter.onCurrentListChanged(
                        adapter.currentList,
                        it
                    )
                }
            }
            adapter.notifyDataSetChanged()
            swipeLayout.setRefreshing(false)
        }
        /* swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
             android.R.color.holo_green_light,
             android.R.color.holo_orange_light,
             android.R.color.holo_red_light)*/
        return view
    }


    private fun initDataSource() {
        moviesModel = MoviesModel()
        tagsModel = TagsModel(TagsDataSourceImpl())
    }
}


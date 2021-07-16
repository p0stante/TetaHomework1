package ru.avtoropova.tetahomework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.features.movies.MoviesDataSourceImpl
import ru.avtoropova.tetahomework1.features.tags.TagsDataSourceImpl

class MainActivity : AppCompatActivity() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var tagsModel: TagsModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataSource()
        val recycler = findViewById<RecyclerView>(R.id.rv_movies)
        val movies = moviesModel.getMovies()
        val adapter =
            MyMoviesAdapter(this) { Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show() }
        adapter.submitList(movies)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this, 2)

        val tagsRecycler = findViewById<RecyclerView>(R.id.rv_tags)
        val tags = tagsModel.getTags()
        val tagsAdapter =
            MyTagsAdapter(this, tags) { Toast.makeText(this, it.tag, Toast.LENGTH_SHORT).show() }
        tagsRecycler.adapter = tagsAdapter
        tagsRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


    }

    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        tagsModel = TagsModel(TagsDataSourceImpl())
    }


}






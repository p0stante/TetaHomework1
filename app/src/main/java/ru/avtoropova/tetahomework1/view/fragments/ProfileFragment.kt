package ru.avtoropova.tetahomework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.adapters.MyTagsAdapter
import ru.avtoropova.tetahomework1.viewmodels.TagsModel

class ProfileFragment : Fragment() {
    private lateinit var rvTags: RecyclerView
    private lateinit var tagsModel: TagsModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        rvTags = view.findViewById(R.id.rv_interests)
        val tagsAdapter = MyTagsAdapter {
            Toast.makeText(
                context,
                it.tag,
                Toast.LENGTH_SHORT
            ).show()
        }
        tagsModel = TagsModel()
        tagsModel.tags.observe(viewLifecycleOwner, Observer(tagsAdapter::initData))
        tagsModel.getTags()

        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        return view
    }
}
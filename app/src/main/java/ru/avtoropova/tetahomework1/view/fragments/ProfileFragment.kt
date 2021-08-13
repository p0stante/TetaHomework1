package ru.avtoropova.tetahomework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.avtoropova.tetahomework1.R
import ru.avtoropova.tetahomework1.adapters.MyTagsAdapter
import ru.avtoropova.tetahomework1.model.room.entities.User
import ru.avtoropova.tetahomework1.viewmodels.TagsModel
import ru.avtoropova.tetahomework1.viewmodels.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var rvTags: RecyclerView
    private val tagsModel: TagsModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()


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

        tagsModel.tags.observe(viewLifecycleOwner, Observer(tagsAdapter::initData))
        tagsModel.getTags()

        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        userViewModel.userData.observe(viewLifecycleOwner) {
            with(requireView()) {
                findViewById<TextView>(R.id.tv_username).text = it.name
                findViewById<TextView>(R.id.tv_mail).text = it.mail
                findViewById<EditText>(R.id.et_name).setText(it.name)
                findViewById<EditText>(R.id.et_password).setText(it.password)
                findViewById<EditText>(R.id.et_mail).setText(it.mail)
                findViewById<EditText>(R.id.et_phone).setText(it.phone)
                findViewById<EditText>(R.id.et_name).setText(it.name)
            }
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        val user: User = userViewModel.userData.value!!
        with(requireView()) {
            user.name = findViewById<EditText>(R.id.et_name).text.toString()
            user.password = findViewById<EditText>(R.id.et_password).text.toString()
            user.mail = findViewById<EditText>(R.id.et_mail).text.toString()
            user.phone = findViewById<EditText>(R.id.et_phone).text.toString()
        }
        userViewModel.updateUser(user)
    }


}
package com.example.retrofit.view.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentPostsBinding
import com.example.retrofit.model.Post

class PostsFragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_posts, container, false)
        viewModel = ViewModelProvider(this)[PostsViewModel::class.java]
        viewModel.getPosts()
        var adapter = PostsAdapter(activity?.applicationContext!!)
        adapter.onCLickListener = object : PostsAdapter.OnCLickListener {
            override fun onCLick(pos: Int) {
                goToUserFragment(viewModel.posts.value!![pos].userId)
            }

        }
        binding.postsRecyclerView.adapter = adapter
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        viewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            adapter.posts = posts
            binding.progressBar.visibility = View.GONE
        })
        return binding.root
    }
    private fun goToUserFragment(id:Int){
        val action=PostsFragmentDirections.actionPostsFragmentToUserFragment()
        action.userId=id
        findNavController().navigate(action)
    }
}
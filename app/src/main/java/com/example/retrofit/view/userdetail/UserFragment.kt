package com.example.retrofit.view.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val args by navArgs<UserFragmentArgs>()
        println(args.userId)
        viewModel.setTheUserById(args.userId)
        viewModel.user.observe(viewLifecycleOwner, Observer { user->
            binding.user=user
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                back()
            }
        })
    }

    private fun back() {
        val action = UserFragmentDirections.actionUserFragmentToPostsFragment()
        findNavController().navigate(action)
    }
}
package com.osmanuslu.myapplication.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.osmanuslu.myapplication.R
import com.osmanuslu.myapplication.adapter.RecyclerViewAdapterNews
import com.osmanuslu.myapplication.databinding.FragmentMainBinding
import com.osmanuslu.myapplication.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerViewAdapter : RecyclerViewAdapterNews
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        viewModel.data1.observe(viewLifecycleOwner, Observer { newsModel ->
            newsModel?.let {
                recyclerViewAdapter.updateNewsList(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        viewModel = NewsViewModel()

        recyclerViewAdapter = RecyclerViewAdapterNews(ArrayList())
        binding?.recyclerView?.adapter = recyclerViewAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.signout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.signout -> {
                auth.signOut()
                val action = NewsFragmentDirections.actionNewsFragmentToLoginFragment()
                view.let {
                    if (it != null) {
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            }
        }
        return true
    }

}
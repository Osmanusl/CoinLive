package com.osmanuslu.myapplication.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.osmanuslu.myapplication.R
import com.osmanuslu.myapplication.adapter.RecyclerViewAdapter
import com.osmanuslu.myapplication.databinding.FragmentMainBinding
import com.osmanuslu.myapplication.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerViewAdapter : RecyclerViewAdapter
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        viewModel.data.observe(viewLifecycleOwner, Observer { cryptoModel ->
            cryptoModel?.let {
                recyclerViewAdapter.updateModelList(it)
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
//recycler adaptörü fragemntı
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        viewModel = MainViewModel()

        recyclerViewAdapter = RecyclerViewAdapter(ArrayList())
        binding?.recyclerView?.adapter = recyclerViewAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.signout_menu, menu)
    }
  //signout çıkış
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.signout -> {
                auth.signOut()
                val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
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

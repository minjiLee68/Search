package com.sophia.search

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sophia.search.Room.Infor
import com.sophia.search.ViewModel.AppViewModel
import com.sophia.search.ViewModel.AppViewModelFactory
import com.sophia.search.databinding.FragInformationBinding

class InforFragment() : Fragment(), InforAdapter.ItemListener {

    private var _binding: FragInformationBinding ?= null
    val binding: FragInformationBinding
        get() = _binding!!

    private lateinit var adapter : InforAdapter

    private val viewModel by activityViewModels<AppViewModel> {
        AppViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setObserve()
        initRecyclerview()

    }

    private fun setObserve() {
        viewModel.inforListLiveData().observe(
            viewLifecycleOwner,
            {
                (binding.recyclerview.adapter as InforAdapter).submitList(it)
            }
        )
    }


    private fun initRecyclerview() {
        binding.recyclerview.let {
            it.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            it.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            it.adapter = InforAdapter(this@InforFragment)
            it.setHasFixedSize(true)
        }
    }

    override fun onItemLongClick(position: Int) {
        viewModel.deleteinfor(position)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
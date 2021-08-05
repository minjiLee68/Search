package com.sophia.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sophia.search.ViewModel.AppViewModel
import com.sophia.search.ViewModel.AppViewModelFactory
import com.sophia.search.databinding.FragInformationBinding

class InforFragment() : Fragment(), InforAdapter.ItemListener {

    private var _binding: FragInformationBinding ?= null
    val binding: FragInformationBinding
        get() = _binding!!

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
        viewModel.inforListLiveData.observe(
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
        }
    }

//    override fun onItemClick(position: Int) {
//       viewModel.run {
//           setEditMemo(position)
//       }
//    }

    override fun onItemLongClick(position: Int) {
        viewModel.deleteinfor(position)
    }


}
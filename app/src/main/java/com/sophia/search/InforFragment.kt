package com.sophia.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sophia.search.Room.Infor
import com.sophia.search.ViewModel.AppViewModel
import com.sophia.search.ViewModel.AppViewModelFactory
import com.sophia.search.databinding.FragInformationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.coroutines.launch

class InforFragment() : Fragment(), InforAdapter.ItemListener {

    private var _binding: FragInformationBinding ?= null
    val binding: FragInformationBinding
        get() = _binding!!

    private lateinit var adapter: InforAdapter

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

        viewModel.allProducts.observe(
            viewLifecycleOwner, {
                products
                ->
                (binding.recyclerview.adapter as InforAdapter).submitList(products)
            }
        )

        setObserve()
        initRecyclerview()
        searchText()

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

    private fun searchText() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
               viewModel.searchNameChanged(s.toString())
            }

        })
    }

    override fun onItemLongClick(position: Int) {
        viewModel.deleteinfor(position)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
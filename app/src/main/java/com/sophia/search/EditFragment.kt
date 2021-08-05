package com.sophia.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sophia.search.ViewModel.AppViewModel
import com.sophia.search.ViewModel.AppViewModelFactory
import com.sophia.search.databinding.FragEditBinding

class EditFragment() : Fragment() {

    private var _binding: FragEditBinding ?= null
    val binding: FragEditBinding
        get() = _binding!!

    private val viewModel by activityViewModels<AppViewModel> {
        AppViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserve()
        setListener()
    }

    fun setObserve() {
        viewModel.editLiveData.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    binding.run {
                        etName.setText(it.name)
                        etPhnumber.setText(it.phnumber)
                    }
                }
            }
        )
    }

    fun setListener() {
        binding.clickButton.setOnClickListener {
            viewModel.saveinfor(
                binding.etName.text.toString(),
                binding.etPhnumber.text.toString()
            )
            binding.etName.text.clear()
            binding.etPhnumber.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
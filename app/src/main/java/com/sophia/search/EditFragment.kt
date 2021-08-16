package com.sophia.search

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.sophia.search.ViewModel.AppViewModel
import com.sophia.search.ViewModel.AppViewModelFactory
import com.sophia.search.databinding.FragEditBinding
import java.util.jar.Manifest

@Suppress("DEPRECATION")
class EditFragment() : Fragment() {

    private var _binding: FragEditBinding ?= null
    val binding: FragEditBinding
        get() = _binding!!

    private val viewModel by activityViewModels<AppViewModel> {
        AppViewModelFactory(requireActivity().application)
    }

    private val galleryRequestCode = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
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
                        imageGlide.setImageResource(galleryRequestCode)
                    }
                }
            }
        )
    }

    fun setListener() {
        binding.clickButton.setOnClickListener {
            viewModel.saveinfor(
                binding.etName.text.toString(),
                binding.etPhnumber.text.toString(),
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
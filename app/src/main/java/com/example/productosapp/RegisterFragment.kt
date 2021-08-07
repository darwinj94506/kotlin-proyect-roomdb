package com.example.productosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.productosapp.databinding.FragmentDetailProductBinding
import com.example.productosapp.databinding.FragmentRegisterBinding

class RegisterFragment :Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.registerStatus.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }

    companion object {
        fun newInstance(): RegisterFragment {
            val fragment = RegisterFragment()
            return fragment
        }
    }

}
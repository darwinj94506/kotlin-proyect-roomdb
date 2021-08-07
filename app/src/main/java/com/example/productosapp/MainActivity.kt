package com.example.productosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.productosapp.databinding.ActivityRootBinding
import com.example.productosapp.databinding.FragmentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)

        supportFragmentManager.beginTransaction()
            . add(R.id.container, MainFragment.newInstance(), "MainFragment")
            .commit()
    }
}

class MainFragment: Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.stateLogin.observe(viewLifecycleOwner) { status ->
            if(status){
                val intent = Intent(requireContext(), ProductActivity::class.java)
                requireContext().startActivity(intent)
            }

        }

        binding.registerButton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance(), "Register")
                .addToBackStack("Register")
                .commit()
        }

        viewModel.registerStatus.observe(viewLifecycleOwner) { status ->
            if(status==true){
                val intent = Intent(requireContext(), MainActivity::class.java)
                requireContext().startActivity(intent)
            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(): MainFragment{
            val args = Bundle()

            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
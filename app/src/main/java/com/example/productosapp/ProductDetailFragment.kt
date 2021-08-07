package com.example.productosapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.productosapp.data.Product
import com.example.productosapp.databinding.FragmentDetailProductBinding

class ProductDetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailProductBinding
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.createdProduct.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }


        val productId = arguments?.getInt("product_id") ?: -1
        viewModel.start(productId)

        return binding.root
    }

    companion object {

        fun newInstance(productId: Int? = null): ProductDetailFragment {
            val args = bundleOf("product_id" to productId)

            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }

    }
}
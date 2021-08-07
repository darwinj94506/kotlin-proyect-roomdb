package com.example.productosapp
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productosapp.data.TASK_DIFF
import com.example.productosapp.data.Product
import com.example.productosapp.databinding.FragmentProductBinding
import com.example.productosapp.databinding.ItemProductBinding

class ProductFragment: Fragment() {

    private lateinit var binding: FragmentProductBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductBinding.inflate(inflater, container, false)

        adapter = Adapter(viewModel)
        binding.recyclerView.adapter = adapter

        binding.createProductButton.setOnClickListener {
            // New fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ProductDetailFragment.newInstance(), "ProductDetail")
                .addToBackStack("ProductDetail")
                .commit()
        }

        viewModel.product.observe(viewLifecycleOwner) {
            adapter.submitList(it ?: emptyList())
        }

        viewModel.eventOpenDetail.observe(viewLifecycleOwner) {

            it ?: return@observe

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ProductDetailFragment.newInstance(it.id), "ProductDetail")
                .addToBackStack("ProductDetail")
                .commit()
        }

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    companion object {

        fun newInstance(): ProductFragment {
            val args = Bundle()

            val fragment = ProductFragment()
            fragment.arguments = args
            return fragment
        }

    }

    class Adapter(private val viewModel: ProductViewModel) : ListAdapter<Product, ViewHolder>(TASK_DIFF) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemProductBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.product = getItem(position)
            holder.binding.viewModel = viewModel
            holder.binding.executePendingBindings()
        }

    }

    class ViewHolder (val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

}

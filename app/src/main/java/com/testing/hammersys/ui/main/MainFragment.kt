package com.testing.hammersys.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.testing.hammersys.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainAdapter()
        val promoAdapter = PromoAdapter()
        val categoryAdapter = CategoryAdapter()
        adapter.submitList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        promoAdapter.submitList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        categoryAdapter.submitList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        binding.recycler.recycler.adapter = adapter
        binding.recyclerHorizontal.adapter = categoryAdapter
        binding.recyclerHide.adapter = promoAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

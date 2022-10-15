package com.testing.hammersys.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.testing.hammersys.databinding.FragmentMainBinding
import com.testing.hammersys.remote.ApiHolder
import com.testing.hammersys.ui.adapters.CategoryAdapter
import com.testing.hammersys.ui.adapters.FilmAdapter
import com.testing.hammersys.ui.adapters.PromoAdapter
import com.testing.hammersys.viewmodels.MainViewModel
import com.testing.hammersys.viewmodels.ViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = FilmAdapter()
    private val promoAdapter = PromoAdapter()
    private lateinit var viewModel: MainViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.films.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
            categoryAdapter.notifyDataSetChanged()
        }
        viewModel.promo.observe(viewLifecycleOwner) {
            promoAdapter.submitList(it)
        }
        binding.recyclerFilms.recyclerFilms.adapter = adapter
        binding.recyclerCategory.adapter = categoryAdapter
        binding.recyclerPromo.adapter = promoAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(
                this, ViewModelFactory(requireActivity().application, ApiHolder)
            )[MainViewModel::class.java]
        categoryAdapter = CategoryAdapter(viewModel)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

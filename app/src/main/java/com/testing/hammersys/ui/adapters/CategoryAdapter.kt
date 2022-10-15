package com.testing.hammersys.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.testing.hammersys.databinding.RecycleCategoryItemBinding
import com.testing.hammersys.model.Category
import com.testing.hammersys.viewmodels.MainViewModel

class CategoryAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Category, CategoryAdapter.PromoViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        return PromoViewHolder(
            RecycleCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            button.setOnClickListener {
                if (button.isChecked) viewModel.categoryClickListener?.invoke(this)
                else viewModel.restoreData()
            }
        }
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PromoViewHolder(private val binding: RecycleCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val button = binding.categoryName

        fun bind(category: Category) = with(binding) {
            button.isChecked = category.isChecked
            categoryName.text = category.name
        }
    }
}

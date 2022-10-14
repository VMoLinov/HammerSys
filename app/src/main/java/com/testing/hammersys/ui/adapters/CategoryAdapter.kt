package com.testing.hammersys.ui.adapters

import android.graphics.drawable.TransitionDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.testing.hammersys.R
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
        ).apply { itemView.setOnClickListener { viewModel.categoryClickListener?.invoke(itemView) } }
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PromoViewHolder(private val binding: RecycleCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) = with(binding) {
            categoryContainer.setOnClickListener {
                val transition = it.background as TransitionDrawable
                transition.startTransition(600)
                categoryName.setTextColor(it.context.getColor(R.color.text_selected))
            }
            categoryName.text = category.name
        }
    }
}

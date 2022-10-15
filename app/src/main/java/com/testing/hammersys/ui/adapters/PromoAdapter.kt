package com.testing.hammersys.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.testing.hammersys.databinding.RecyclePromoItemBinding
import com.testing.hammersys.model.Film
import com.testing.hammersys.ui.imageloader.GlideImageLoader
import com.testing.hammersys.ui.imageloader.ImageLoader

class PromoAdapter : ListAdapter<Film, PromoAdapter.PromoViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        return PromoViewHolder(
            RecyclePromoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PromoViewHolder(private val binding: RecyclePromoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageLoader: ImageLoader = GlideImageLoader()

        fun bind(film: Film) {
            imageLoader.loadFilmBackDrop(film.backdrop_path, binding.backDrop)
        }
    }
}

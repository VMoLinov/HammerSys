package com.testing.hammersys.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.testing.hammersys.databinding.RecyclerFilmItemBinding
import com.testing.hammersys.model.Film
import com.testing.hammersys.ui.imageloader.GlideImageLoader
import com.testing.hammersys.ui.imageloader.ImageLoader

class FilmAdapter : ListAdapter<Film, FilmAdapter.AdapterViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.overview == newItem.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            RecyclerFilmItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class AdapterViewHolder(private val binding: RecyclerFilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageLoader: ImageLoader = GlideImageLoader()

        fun bind(film: Film) = with(binding) {
            imageLoader.loadFilmPoster(film.poster_path, poster)
            title.text = film.title
            description.text = film.overview
        }
    }
}

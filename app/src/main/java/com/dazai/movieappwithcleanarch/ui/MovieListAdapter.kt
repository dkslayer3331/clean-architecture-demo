package com.dazai.movieappwithcleanarch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.databinding.ItemMovieBinding
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.ui.utils.showImage

class MovieListAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Movie, MovieViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {
            movieId = getItem(position).id
            with(binding) {
                tvMovieTitle.text = getItem(position).title
                ivPoster.showImage(getItem(position).posterPath)
            }
        }
    }
}

class MovieViewHolder(val binding: ItemMovieBinding, val onClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    var movieId = 0 // which could also be value object

    init {
        binding.root.setOnClickListener {
            onClick(movieId)
        }
    }

}

val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title

}
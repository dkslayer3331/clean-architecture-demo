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
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.ui.utils.showImage

class MovieListAdapter(private val onClick : (Int) -> Unit) : ListAdapter<Movie, MovieViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = with(holder){
        moviePoster.showImage(getItem(position).posterPath)
        movieTitle.text = getItem(position).title
        movieId = getItem(position).id
    }
}

class MovieViewHolder(itemView: View, val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView){

    var movieId = 0 // which could also be value object

    val movieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    val moviePoster: ImageView = itemView.findViewById(R.id.ivPoster)

    init {
        itemView.setOnClickListener {
            onClick(movieId)
        }
    }

}

val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title

}
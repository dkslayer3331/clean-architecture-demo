package com.dazai.movieappwithcleanarch.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.showImage

class MovieListAdapter : ListAdapter<MovieEntity, MovieViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.moviePoster.showImage(getItem(position).posterPath)
        holder.movieTitle.text = getItem(position).title
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    val moviePoster: ImageView = itemView.findViewById(R.id.ivPoster)
}

val diffUtil = object : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) = oldItem.equals(newItem)

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) = oldItem.title == newItem.title

}
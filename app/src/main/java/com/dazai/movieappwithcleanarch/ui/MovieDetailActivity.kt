package com.dazai.movieappwithcleanarch.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.ui.utils.showImage
import com.dazai.movieappwithcleanarch.ui.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private val detailViewModel: MovieDetailViewModel by viewModels()

    private val tvTitle by lazy {
        findViewById<TextView>(R.id.tvTitle)
    }

    private val tvOverView by lazy {
        findViewById<TextView>(R.id.tvOverview)
    }

    private val tvGenres by lazy {
        findViewById<TextView>(R.id.tvGenres)
    }

    private val tvReleaseYear by lazy {
        findViewById<TextView>(R.id.tvReleaseYear)
    }

    private val tvRatingBar by lazy {
        findViewById<RatingBar>(R.id.ratingBar)
    }

    private val ivMoviePoster by lazy {
        findViewById<ImageView>(R.id.ivMovieDetailPoster)
    }

    companion object {
        const val IE_MOVIE_ID = "movie_id"
        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(IE_MOVIE_ID, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra(IE_MOVIE_ID, 0)

        detailViewModel.getMovieDetail(movieId)

        detailViewModel.errorEvent.observe(this, Observer {
            showToast(it)
        })

        detailViewModel.successEvent.observe(this, Observer {
            with(it) {
                tvTitle.text = title
                tvOverView.text = overview
                tvGenres.text = genres
                tvRatingBar.rating = voteAverage
                ivMoviePoster.showImage(posterPath)
                tvReleaseYear.text = releaseYear
            }
        })

    }
}
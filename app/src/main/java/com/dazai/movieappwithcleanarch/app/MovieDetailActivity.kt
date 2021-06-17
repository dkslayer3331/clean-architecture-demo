package com.dazai.movieappwithcleanarch.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dazai.movieappwithcleanarch.R

class MovieDetailActivity : AppCompatActivity() {

    private val detailViewModel : MovieDetailViewModel by viewModels()

    companion object{
        const val IE_MOVIE_ID = "movie_id"
        fun newIntent(context: Context, id : Int) : Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(IE_MOVIE_ID, id)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra(MovieDetailActivity.IE_MOVIE_ID,0)

        detailViewModel.getMovieDetail(movieId)

        detailViewModel.viewState.observe(this, Observer {
            if(it is Resource.Success){
                it.data?.let {
                    Log.d("MovieDetail","$it")
                }
            }
        })

    }
}
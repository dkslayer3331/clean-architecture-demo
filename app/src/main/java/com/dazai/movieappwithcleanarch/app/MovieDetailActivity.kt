package com.dazai.movieappwithcleanarch.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.app.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        val movieId = intent.getIntExtra(IE_MOVIE_ID,0)

       // Log.d("movieId","$movieId")

        detailViewModel.getMovieDetail(movieId)

        detailViewModel.viewState.observe(this, Observer {
            if(it is Resource.Success){
                it.data?.let {
                    Log.d("MovieDetail","$it")
                }
            }
            else if(it is Resource.Error){
                showToast(it.message ?: return@Observer)
            }
        })

    }
}
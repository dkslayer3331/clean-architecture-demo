package com.dazai.movieappwithcleanarch.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    lateinit var movieListAdapter: MovieListAdapter

    lateinit var progressBar: ProgressBar

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        recyclerView = findViewById(R.id.rvMovies)

        movieListAdapter = MovieListAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
        }

        viewModel.movies.observe(this, Observer {
            Log.d("movies",it.size.toString())
            progressBar.visibility = View.GONE
            movieListAdapter.submitList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            if(it.isNotEmpty()) {
                progressBar.visibility = View.GONE
                this.showToast(it)
            }
        })

    }
}
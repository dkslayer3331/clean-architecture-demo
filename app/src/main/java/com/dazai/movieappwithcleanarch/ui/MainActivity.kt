package com.dazai.movieappwithcleanarch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.ui.utils.ItemDecoration
import com.dazai.movieappwithcleanarch.ui.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

     private val movieListAdapter : MovieListAdapter by lazy{
         MovieListAdapter{
             val intent = MovieDetailActivity.newIntent(this, it)
             startActivity(intent)
         }
     }

    lateinit var progressBar: ProgressBar

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        recyclerView = findViewById(R.id.rvMovies)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieListAdapter
            addItemDecoration(ItemDecoration(8))
        }

        viewModel.showErrorEvent.observe(this, Observer {
            progressBar.visibility = View.GONE
            showToast(it)
        })

        viewModel.showLoadingEvent.observe(this, Observer {
            progressBar.visibility = View.VISIBLE
        })

        viewModel.showMoviesEvent.observe(this, Observer {
            progressBar.visibility = View.GONE
            movieListAdapter.submitList(it)
        })

    }
}
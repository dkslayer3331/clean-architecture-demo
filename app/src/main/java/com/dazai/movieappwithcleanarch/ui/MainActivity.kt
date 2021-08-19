package com.dazai.movieappwithcleanarch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.databinding.ActivityMainBinding
import com.dazai.movieappwithcleanarch.ui.utils.ItemDecoration
import com.dazai.movieappwithcleanarch.ui.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

     private val movieListAdapter : MovieListAdapter by lazy{
         MovieListAdapter{
             val intent = MovieDetailActivity.newIntent(this, it)
             startActivity(intent)
         }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieListAdapter
            addItemDecoration(ItemDecoration(8))
        }

        viewModel.showErrorEvent.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            showToast(it)
        })

        viewModel.showLoadingEvent.observe(this, Observer {
            binding.progressBar.visibility = View.VISIBLE
        })

        viewModel.showMoviesEvent.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            movieListAdapter.submitList(it)
        })


    }

}
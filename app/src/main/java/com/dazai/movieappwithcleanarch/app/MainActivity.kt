package com.dazai.movieappwithcleanarch.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dazai.movieappwithcleanarch.R
import com.dazai.movieappwithcleanarch.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.tvHelloWorld)

        viewModel.movies.observe(this, Observer {
            Log.d("movies",it.size.toString())
            //this.showToast(it.size.toString())
            textView.text = "${it.size}"
        })

        viewModel.errorMessage.observe(this, Observer {
            if(it.isNotEmpty()) this.showToast(it)
        })

    }
}
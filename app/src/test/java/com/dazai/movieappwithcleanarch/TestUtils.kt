package com.dazai.movieappwithcleanarch

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dazai.movieappwithcleanarch.domain.model.Movie
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun getMockMovies() : List<Movie>{
    return (1..10).map { i ->
        Movie(
            id = i,
            title = "title$i",
            originalTitle = "originalTitle$i",
            voteAverage = 3.5f,
            posterPath = "posterPath$i",
            genres = "action, thriller, mystery",
            overview = "overview$i",
            releaseYear = "year$i"
        )
    }
}

fun getMockedMovie() = Movie(
        id = 3,
        title = "title3",
        originalTitle = "originalTitle3",
        voteAverage = 3.5f,
        posterPath = "posterPath3",
        genres = "action, thriller, mystery",
        overview = "overview3",
        releaseYear = "year3"
)

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    afterObserve.invoke()

    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
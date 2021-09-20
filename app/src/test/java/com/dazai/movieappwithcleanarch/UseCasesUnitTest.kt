package com.dazai.movieappwithcleanarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dazai.movieappwithcleanarch.data.repositories.FakeRepoImpl
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.ErrorUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieDetailUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCaseImpl
import com.dazai.movieappwithcleanarch.ui.MainViewModel
import com.dazai.movieappwithcleanarch.ui.MovieDetailViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class UseCasesUnitTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule() // for livedata

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    lateinit var mockItems : List<Movie>

    lateinit var movieListUseCase: GetMovieListUseCase

    lateinit var mainViewModel: MainViewModel

    lateinit var detailViewModel: MovieDetailViewModel

    lateinit var detailUseCaseImpl: GetMovieDetailUseCase

    private lateinit var fakeRepo : FakeRepoImpl

     private val fakeMovieListUseCaseImpl by lazy {
        FakeMovieListUseCaseImpl()
    }

    private val fakeDetailUseCaseImpl by lazy {
        FakeMovieDetailUsecaseImpl()
    }


    @Before
    fun setup() {
        mockItems = getMockMovies()
        val errorUseCase = mock<ErrorUseCase>()
        fakeRepo = FakeRepoImpl()
        detailUseCaseImpl = FakeMovieDetailUsecaseImpl()
        movieListUseCase = GetMovieListUseCaseImpl(errorUseCase, fakeRepo)
        mainViewModel = MainViewModel(movieListUseCase)
        detailViewModel = MovieDetailViewModel(detailUseCaseImpl)
    }

    @After
    fun clear(){
        fakeRepo.resetErrStatus()
    }

    // testing live data inside viewmodel

    @Test
    fun `verifying that loading state showing at proper time when fetching movie list` (){
        coroutinesTestRule.pauseDispatcher() // to catch initial states

        mainViewModel.showMovies()

        assertThat(mainViewModel.showLoadingEvent.getOrAwaitValue(), `is` (true))

        coroutinesTestRule.resumeDispatcher()

        assertThat(mainViewModel.showLoadingEvent.getOrAwaitValue(), `is` (false))

    }

    @Test
    fun `verifying error state showing in relevant situation when fetching movie list`(){
        fakeMovieListUseCaseImpl.setErrorReturn()

        mainViewModel = MainViewModel(fakeMovieListUseCaseImpl)

        mainViewModel.showMovies()

        assertThat(mainViewModel.showErrorEvent.getOrAwaitValue(), `is` ("error something"))

    }

    @Test
    fun `making sure that movie list use case is getting list of movies`(){
        mainViewModel.showMovies()
        assertThat(mainViewModel.showMoviesEvent.getOrAwaitValue(), `is` (mockItems))
    }

    @Test
    fun `verifying that loading state showing at proper time when fetching movie detail`(){
        coroutinesTestRule.pauseDispatcher() // to catch initial states

        detailViewModel.getMovieDetail(3)

        assertThat(detailViewModel.loadingEvent.getOrAwaitValue(), `is` (true))

        coroutinesTestRule.resumeDispatcher()

        assertThat(detailViewModel.loadingEvent.getOrAwaitValue(), `is` (false))
    }

    @Test
    fun `verifying error state showing in relevant situation when fetching movie detail`(){
        fakeDetailUseCaseImpl.setErrStatus(true)

        detailViewModel = MovieDetailViewModel(fakeDetailUseCaseImpl)

        detailViewModel.getMovieDetail(3)

        assertThat(mainViewModel.showErrorEvent.getOrAwaitValue(), `is` ("movie detail error"))

    }

    @Test
    fun `making sure that movie detail use case is getting movie info`(){
        detailViewModel.getMovieDetail(3)
        assertThat(detailViewModel.successEvent.getOrAwaitValue(), `is` (getMockedMovie()))
    }



}
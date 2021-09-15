package com.dazai.movieappwithcleanarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dazai.movieappwithcleanarch.data.repositories.FakeRepoImpl
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.domain.usecases.ErrorUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCaseImpl
import com.dazai.movieappwithcleanarch.ui.MainViewModel
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    lateinit var mockItems : List<Movie>

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var movieListUseCaseSecond: GetMovieListUseCase

    lateinit var mainViewModel: MainViewModel

    lateinit var fakeRepo : FakeRepoImpl

     val fakeMovieListUseCaseImpl by lazy {
        FakeMovieListUseCaseImpl()
    }

    @Before
    fun setup() {
        mockItems = getMockMovies()
        //second test case
        val errorUseCase = mock<ErrorUseCase>()
        val mockRepo = mock<MovieRepository>()
        fakeRepo = FakeRepoImpl()
        movieListUseCaseSecond = GetMovieListUseCaseImpl(errorUseCase, fakeRepo)
        mainViewModel = MainViewModel(movieListUseCaseSecond)
    }

    @After
    fun clear(){
        fakeRepo.resetErrStatus()
    }

    private val movieListUseCase = mock<GetMovieListUseCase>()

    @Test
    fun testMovieListUseCaseSuccess(){

        val successResult = Resource.Success(mockItems)

        testDispatcher.pauseDispatcher()

            runBlockingTest {
                whenever(movieListUseCase.invoke()).thenReturn(successResult)
                testDispatcher.resumeDispatcher()
                assertEquals(mockItems, getMockMovies())
            }
    }

    @Test
    fun `making sure that loading state showing at proper time when fetching movie list` (){
        coroutinesTestRule.pauseDispatcher() // to catch initial states

        mainViewModel.showMovies()

        assertThat(mainViewModel.showLoadingEvent.getOrAwaitValue(), `is` (true))

        coroutinesTestRule.resumeDispatcher()

        assertThat(mainViewModel.showLoadingEvent.getOrAwaitValue(), `is` (false))

    }

    @Test
    fun `make sure error state showing in relevant situation`(){
        fakeMovieListUseCaseImpl.setErrorReturn()
        mainViewModel = MainViewModel(fakeMovieListUseCaseImpl)

        mainViewModel.showMovies()

        assertThat(mainViewModel.showErrorEvent.getOrAwaitValue(), `is` ("error something"))

    }

    @Test
    fun `making sure that movie list use case is getting list of movies`(){
        runBlockingTest {
            val movies = movieListUseCaseSecond.invoke()
            assertEquals(movies!!.data!!.size, 10)
        }
    }

    @Test
    fun `this time for error in repository and return empty list`(){
        fakeRepo.setErrorReturn()
        runBlockingTest {
            val movies = fakeRepo.fetchMovies()
            assertThat(movies.size, `is` (0) )
        }
    }

}
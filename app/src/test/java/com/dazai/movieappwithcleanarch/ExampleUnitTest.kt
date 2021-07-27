package com.dazai.movieappwithcleanarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
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


    @Before
    fun setup() {
        mockItems = getMockMovies()
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




}
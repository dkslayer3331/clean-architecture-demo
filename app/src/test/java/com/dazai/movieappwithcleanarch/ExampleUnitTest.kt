package com.dazai.movieappwithcleanarch

import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.utils.toDbEntity
import com.dazai.movieappwithcleanarch.data.utils.toUseCaseEntity
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_detailMapper(){
        val detail = MovieDetailResponse(
                adult = true,
                releaseDate = "505050",
                overview = "ggwp overview",
                genres = emptyList(),
                posterPath = "/path",
                voteAverage = 5.6,
                originalTitle = "title",
                title = "ttile",
                id = 3456,
                backdropPath = "/path",
                belongsToCollection = null,
                budget = 345,
                homepage = "",
                imdbId = "",
                originalLanguage = "",
                popularity = 3.4,
                productionCompanies = null,
                productionCountries = null,
                revenue = 3,
                runtime = 3,
                spokenLanguages = null,
                status = "",
                tagline = "",
                video = false,
                voteCount = 345
        )
        val entity = detail.toDbEntity()
        val useCaseModel = entity.toUseCaseEntity()
        assertEquals(detail.overview.isNotEmpty(), useCaseModel.overview.isNotEmpty())
    }

}
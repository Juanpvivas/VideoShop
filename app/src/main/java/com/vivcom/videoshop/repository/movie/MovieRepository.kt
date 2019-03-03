package com.vivcom.videoshop.repository.movie

import android.app.Application
import androidx.lifecycle.LiveData
import com.vivcom.videoshop.repository.model.ListMovies
import com.vivcom.videoshop.repository.model.MessageResponse
import com.vivcom.videoshop.repository.persistence.database.BaseResponse
import com.vivcom.videoshop.repository.persistence.database.entity.Movie

class MovieRepository(private val application: Application) {

    private var mRepository = MovieRepositoryBD(application)
    private var mAllMovies: LiveData<List<Movie>> = mRepository.getAllMovies()

    fun getMovies(): LiveData<List<Movie>> {
        MovieRepositoryNet.getMovies(
            application,
            object : BaseResponse<ListMovies>() {
                override fun okResult(resultType: ListMovies?) {
                    mRepository.insertListMovie(resultType?.items!!)
                }

                override fun failResult(msg: MessageResponse?) {

                }

            })
        return mAllMovies
    }

    fun insertMovie(movie: Movie) {
        mRepository.insertMovie(movie)
    }
}
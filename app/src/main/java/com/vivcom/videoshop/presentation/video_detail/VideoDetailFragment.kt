package com.vivcom.videoshop.presentation.video_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vivcom.videoshop.R
import com.vivcom.videoshop.presentation.video.VideoViewModel
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.tool.Constants
import kotlinx.android.synthetic.main.activity_video_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class VideoDetailFragment : Fragment() {
    private var idMovie: String? = null
    private lateinit var mVideoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMovie = it.getString(Constants.Keys.ID_MOVIE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_video_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        mVideoViewModel.getMovieById(idMovie!!).observe(this, Observer<Movie> { movie ->
            if (movie != null) {
                tv_title.text = movie.title
                tv_overview.text = movie.overview
                movie.loadImage(thumbnail, movie.poster_path!!)
                iv_add.onClick { mVideoViewModel.addShoppingCart(movie) }
                iv_delete.onClick { mVideoViewModel.deleteShoppingCart(movie)
                }
            }
        })
    }


}

package com.vivcom.videoshop.presentation.video_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.vivcom.videoshop.R
import com.vivcom.videoshop.presentation.video.VideoViewModel
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.tool.Constants.options
import kotlinx.android.synthetic.main.activity_video_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class VideoDetailFragment : Fragment() {
    private var idMovie: String? = null
    private lateinit var mVideoViewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val safeArgs = VideoDetailFragmentArgs.fromBundle(arguments!!)
        idMovie = safeArgs.idMovie
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
                iv_add.onClick {
                    mVideoViewModel.addShoppingCart(movie)
                    Toast.makeText(
                        context,
                        R.string.add_shopping_cart,
                        Toast.LENGTH_LONG
                    ).show()
                }
                iv_delete.onClick {
                    mVideoViewModel.deleteShoppingCart(movie)
                    Toast.makeText(
                        context,
                        R.string.delete_shopping_cart,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shopping_cart -> {
                findNavController().navigate(R.id.shopping_cart, null, options)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

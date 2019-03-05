package com.vivcom.videoshop.presentation.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.vivcom.videoshop.R
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.tool.Constants
import com.vivcom.videoshop.repository.tool.Constants.options
import kotlinx.android.synthetic.main.content_main.*

class VideoFragment : Fragment() {

    private lateinit var mVideoViewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.content_main, container, false)
    }

    private fun detailMovie(idMovie: String) {
        val bundle = Bundle()
        bundle.putString(Constants.Keys.ID_MOVIE, idMovie)
        findNavController().navigate(R.id.video_detail_fragment, bundle, options)
    }

    private fun addShoppingCart(movie: Movie) {
        mVideoViewModel.addShoppingCart(movie)
        Toast.makeText(
            context,
            R.string.add_shopping_cart,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun deleteShoppingCart(movie: Movie) {
        mVideoViewModel.deleteShoppingCart(movie)
        Toast.makeText(
            context,
            R.string.delete_shopping_cart,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onResume() {
        super.onResume()
        val adapter =
            VideoListAdapter(context!!.applicationContext, ::addShoppingCart, ::deleteShoppingCart, ::detailMovie)
        rv_content.adapter = adapter
        rv_content.layoutManager = LinearLayoutManager(context!!.applicationContext) as RecyclerView.LayoutManager?

        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        mVideoViewModel.getAllMovies().observe(this, Observer<List<Movie>> { movies ->
            adapter.setMovies(movies)
        })
    }

}

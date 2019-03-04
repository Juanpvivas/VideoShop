package com.vivcom.videoshop.presentation.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController

import com.vivcom.videoshop.R
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import kotlinx.android.synthetic.main.content_main.*

class VideoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    private lateinit var mVideoViewModel: VideoViewModel

    private fun detailMovie(movie: Movie) {
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
        findNavController().navigate(R.id.video_detail_fragment)
        //Navigation.createNavigateOnClickListener(R.id.detail, bundle)
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
        rv_content.layoutManager = LinearLayoutManager(context!!.applicationContext)

        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        mVideoViewModel.getAllMovies().observe(this, Observer<List<Movie>> { movies ->
            adapter.setMovies(movies)
        })
    }

}

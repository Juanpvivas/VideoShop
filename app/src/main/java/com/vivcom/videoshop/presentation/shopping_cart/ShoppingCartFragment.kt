package com.vivcom.videoshop.presentation.shopping_cart


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.vivcom.videoshop.R
import com.vivcom.videoshop.presentation.video.VideoViewModel
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : Fragment() {

    private lateinit var mVideoViewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onResume() {
        super.onResume()
        val adapter =
            ShoppingCartListAdapter(context!!.applicationContext)
        rv_content.adapter = adapter
        rv_content.layoutManager = LinearLayoutManager(context!!.applicationContext)

        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        mVideoViewModel.getAllMovies().observe(this, Observer<List<Movie>> { movies ->
            adapter.setMovies(movies)
        })
        mVideoViewModel.getAllShoppingCart()
    }
}

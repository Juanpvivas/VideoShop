package com.vivcom.videoshop.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivcom.videoshop.R
import com.vivcom.videoshop.presentation.video.VideoListAdapter
import com.vivcom.videoshop.presentation.video.VideoViewModel
import com.vivcom.videoshop.repository.persistence.database.entity.Movie

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mVideoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = VideoListAdapter(this)
        rv_content.adapter = adapter
        rv_content.layoutManager =  LinearLayoutManager(this)

        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        mVideoViewModel.getAllMovies().observe(this, Observer<List<Movie>> { movies ->
            adapter.setMovies(movies)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

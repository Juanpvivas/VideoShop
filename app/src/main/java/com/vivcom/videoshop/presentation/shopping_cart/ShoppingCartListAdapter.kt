package com.vivcom.videoshop.presentation.shopping_cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vivcom.videoshop.R
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import org.jetbrains.anko.sdk27.coroutines.onClick


class ShoppingCartListAdapter(
    val context: Context
) : RecyclerView.Adapter<ShoppingCartListAdapter.MovieViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mMovies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = mMovies[position]
        holder.mTitle.text = current.title
        holder.mDescription.visibility = View.GONE
        holder.mThumbnail.visibility = View.GONE
        holder.mAdd.visibility = View.GONE
        holder.mDelete.visibility = View.GONE
    }

    fun setMovies(movies: List<Movie>) {
        mMovies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle = itemView.findViewById<TextView>(R.id.tv_title)!!
        var mDescription = itemView.findViewById<TextView>(R.id.tv_overview)!!
        var mThumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)!!
        var mAdd = itemView.findViewById<ImageView>(R.id.iv_add)
        var mDelete = itemView.findViewById<ImageView>(R.id.iv_delete)
    }
}
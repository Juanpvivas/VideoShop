package com.vivcom.videoshop.repository.persistence.database.entity

import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.vivcom.videoshop.repository.model.BaseModel
import com.vivcom.videoshop.repository.tool.Constants.Url.BASE_IMAGE_URL

@Entity
data class Movie(
    @PrimaryKey
    @NonNull
    var id: String,
    var title: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    @SerializedName("release_date") var date: String? = null
) : BaseModel() {

    fun loadImage(view: ImageView, poster_path: String) {
        val url = (BASE_IMAGE_URL + poster_path).trim()
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}
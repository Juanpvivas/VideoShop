package com.vivcom.videoshop.repository.model

import com.vivcom.videoshop.repository.persistence.database.entity.Movie

class ListMovies : BaseModel() {
    var id: Int? = null
    var name: String? = null
    var items: List<Movie>? = null

    override fun toString(): String {
        return "$name"
    }
}

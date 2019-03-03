package com.vivcom.videoshop.repository.tool

import java.util.regex.Pattern

object Constants {

    object Url {
        const val BASE_URL: String = "https://api.themoviedb.org/"
        const val BASE_IMAGE_URL: String = " https://image.tmdb.org/t/p/w45"

        object Movie {
            const val GET_LIST: String = "3/list/1"
        }
    }

    object API {
        const val API_KEY: String = "api_key"
        const val VALUE: String = "1af8b49fbf6fe76ff1e0dd6f2e8f0560"
        const val list_id: Int = 1
    }

    object HTTPMethod {
        const val POST: String = "post"
        const val GET: String = "Get"
        const val OAUTH: String = "OAtuh"
        const val TIMEOUT: Long = 20
    }

    object HttpCodes {
        const val OK: Int = 200
        const val UNAUTHORIZED: Int = 401
    }

}
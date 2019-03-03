package com.vivcom.videoshop.repository

import android.content.Context
import com.vivcom.videoshop.repository.connection.Proxy
import com.vivcom.videoshop.repository.model.ListMovies
import com.vivcom.videoshop.repository.model.MessageResponse
import com.vivcom.videoshop.repository.persistence.database.BaseResponse
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.tool.Constants

class MovieRepositoryNet {
    companion object {

        fun getMovies(context: Context, response: BaseResponse<ListMovies>) {
            fun okResponse(obj: Any?) {
                response.okResult(obj as ListMovies)
            }

            fun failResponse(msg: MessageResponse?) {
                response.failResult(msg)
            }

            val url = Constants.Url.Movie.GET_LIST
            val queries: HashMap<String, String> = HashMap()
            queries[Constants.API.API_KEY] = Constants.API.VALUE

            Proxy.execute(
                url = url,
                methodType = Constants.HTTPMethod.GET,
                body = null,
                queries = queries,
                headers = null,
                type = ListMovies::class.java,
                isArray = false,
                context = context,
                okAnswer = ::okResponse,
                failedAnswer = ::failResponse
            )

        }
    }
}
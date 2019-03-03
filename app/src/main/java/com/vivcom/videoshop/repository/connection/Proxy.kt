package com.vivcom.videoshop.repository.connection

import android.content.Context
import android.util.Log

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.vivcom.videoshop.repository.model.BaseModel
import com.vivcom.videoshop.repository.model.MessageResponse
import com.vivcom.videoshop.repository.tool.Constants
import com.vivcom.videoshop.repository.tool.Utils
import com.vivcom.videoshop.repository.tool.toJsonString
import org.jetbrains.annotations.NotNull
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Proxy<T : BaseModel> {

    companion object {
        fun execute(
            url: String,
            methodType: String,
            body: BaseModel?,
            queries: Map<String, String>?,
            headers: HashMap<String, String>?,
            type: Class<out BaseModel>,
            isArray: Boolean,
            context: Context,
            okAnswer: ((Any?) -> Unit),
            failedAnswer: ((MessageResponse?) -> Unit)
        ) {
            if (!verifyInternetConnection(context, failedAnswer)) {
                return
            }

            val proxyEndpoint: IProxyEndPoints = ProxyAdapter.startConnection(Constants.Url.BASE_URL)
            val requestQueries: Map<String, String> = queries ?: HashMap()
            val requestHeaders: HashMap<String, String> = headers ?: HashMap()

            var call: Call<Any>? = null

            when (methodType) {
                Constants.HTTPMethod.GET -> call = proxyEndpoint.getData(url, requestQueries, requestHeaders)
                Constants.HTTPMethod.OAUTH -> call = proxyEndpoint.oAuth(url, body = queries as Map<String, String>)
                Constants.HTTPMethod.POST -> {
                    if (body == null)
                        call = proxyEndpoint.postData(url, query = requestQueries, header = requestHeaders)
                    else
                        call = proxyEndpoint.postData(url, query = requestQueries, header = requestHeaders, body = body)
                }
            }

            call?.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                    when (response?.code()) {
                        Constants.HttpCodes.OK -> {
                            var objectResponse: Any? = null
                            if (response.body() != null) {

                                if (response.body() is LinkedTreeMap<*, *>) {
                                    val model = BaseModel.objectFromJson(
                                        (response.body() as LinkedTreeMap<Any, Any>).toJsonString(),
                                        type
                                    )
                                    okAnswer.invoke(model)

                                } else if (response.body() is ArrayList<*>) {
                                    val array: ArrayList<out BaseModel>
                                    try {
                                        array = BaseModel.arrayFromJson(
                                            response.body() as ArrayList<LinkedTreeMap<Any, Any>>,
                                            type
                                        )
                                        okAnswer.invoke(array)


                                    } catch (e: Exception) {

                                        val tmpGSon = Gson()
                                        val mijson = tmpGSon.toJson(response.body())
                                        fun <K : Any?> JSONArrayToArrayObject(@NotNull json: String, @NotNull clase: Class<K>): MutableList<K>? {
                                            val jsonArray = JSONArray(json)
                                            var list: MutableList<K> = emptyList<K>().toMutableList()
                                            try {
                                                val gson = Gson()
                                                for (contador in 0 until jsonArray.length()) {
                                                    val tmp = gson.fromJson(jsonArray[contador].toString(), clase)
                                                    list.add(tmp)
                                                }
                                            } catch (e: Exception) {
                                                Log.e("Error", Log.getStackTraceString(e))
//                                                list = emptyList<K>().toMutableList()
                                            }
                                            return list
                                        }
                                        okAnswer.invoke(JSONArrayToArrayObject(mijson, type))

                                    }
                                } else {
                                    okAnswer.invoke(null)
                                }
                            } else {
                                okAnswer.invoke(objectResponse)
                            }
                        }

                        else -> {
                            val messageResponse: MessageResponse
                            if (response?.errorBody() == null || response.errorBody()?.string()?.isEmpty()!!) {
                                messageResponse =
                                    MessageResponse(response?.code()!!, "Fallo la comunicacion con el servicio")
                                val tmp = response.raw().toString()
                                Log.e("Error", tmp)
                            } else {
                                val tmp = response.raw().toString()
                                Log.e("Error", tmp)
                                messageResponse = MessageResponse(response.code(), response.errorBody()?.string())
                            }
                            failedAnswer.invoke(messageResponse)
                        }
                    }
                }

                override fun onFailure(call: Call<Any>?, t: Throwable?) {
                    try {
                        val newMessageResponse: MessageResponse =
                            BaseModel.objectFromJson(t.toString(), MessageResponse::class.java) as MessageResponse
                        newMessageResponse.code = t?.hashCode()
                        failedAnswer.invoke(newMessageResponse)

                    } catch (e: Exception) {
                        val messageResponse = MessageResponse(t?.hashCode()!!, t.toString())
                        failedAnswer.invoke(messageResponse)
                    }
                }
            })
        }


        private fun verifyInternetConnection(
            context: Context,
            failedAnswer: ((MessageResponse?) -> Unit)? = null
        ): Boolean {
            if (!Utils.isNetworkAvailable(context)) {
                val messageResponse = MessageResponse(401, "Falla en los servicios de red")
                failedAnswer?.invoke(messageResponse)
                return false
            }
            return true
        }


    }
}

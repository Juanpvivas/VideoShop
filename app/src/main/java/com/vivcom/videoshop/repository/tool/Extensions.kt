package com.vivcom.videoshop.repository.tool

import com.google.gson.internal.LinkedTreeMap
import java.lang.Exception

fun LinkedTreeMap<*, *>.toJsonString(): String {

    var jsonString = "{"
    var count = 0

    for (i in this) {

        jsonString += "\"" + i.key + "\" : "
        if (i.value is String) {
            jsonString += "\"" + (i.value as String).replace("\"", "'") + "\""
        } else if (i.value is LinkedTreeMap<*, *>) {
            jsonString += (i.value as LinkedTreeMap<*, *>).toJsonString()
        } else if (i.value is ArrayList<*>) {
            val array = i.value as ArrayList<LinkedTreeMap<*, *>>
            if (array.isEmpty()) {
                jsonString += "[]"
            } else {
                jsonString += "["
                var countArray = 0
                try {
                    for (j in array) {
                        jsonString += j.toJsonString()
                        countArray++
                        if (countArray < array.size) {
                            jsonString += ","
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                jsonString += "]"
            }

        } else {
            jsonString += i.value
        }
        count++
        if (count < this.size) {
            jsonString += ","
        }
    }
    jsonString += "}"
    return jsonString
}
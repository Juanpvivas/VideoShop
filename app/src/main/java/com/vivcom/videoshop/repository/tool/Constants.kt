package com.vivcom.videoshop.repository.tool

import java.util.regex.Pattern

object Constants {

    object Url {
        const val URL_BASE: String = "https://base-93d65.firebaseio.com/"
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

    object DialogKeys {
        const val TYPE: String = "type"
        const val TITLE: String = "title"
        const val MESSAGE: String = "message"
        const val POSITIVE_TEXT: String = "positive text"
        const val NEGATIVE_TEXT: String = "negative text"
    }

    object DialogSizes {
        val standarWidth = 90
        val standarHeight = 35
    }

    object RegExps {
        val NUMBER_WHITOUT_SPECIAL_CARACTERS = "[0-9]+"
        val WHITOUT_SPECIAL_CARACTERS = Pattern.compile("[A-Z0-9a-zñÑ]{4,10}")
        val LENGHT_3_OR_MORE = ".{3,}"
        val LENGHT_5_OR_MORE = ".{5,}"
        val LENGHT_7_OR_MORE = ".{7,}"
        val LENGHT_10_OR_MORE = ".{10,}"
        val PATTERN_EMAIL =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z,]{2,})$")
        val PATTERN_PASSWORD = "(?=.*[0-9])(?=.*[A-Z])([^\\s]){6,15}"
        val PATTERN_VALIDATION_CODE = "(?:\\b\\d{6}\\b)"
        val PATTERN_PHONE = Pattern.compile("[0-9]{7,10}")
        val CURRENCY_TO_LONG = Pattern.compile("[\$,.]")
    }

    object DialogType {
        val INFO = 0
        val OK = 1
        val WARNING = 2
        val ERROR = 3
        val TEXT_AREA = 4
        val EMAIL_SEND = 5
        val NOT_INTERNET = 6
        val NOT_USER = 7
        val CAMERA = 8
        val GALERY = 9
        val ALERT = 10
    }

    object Date {
        const val SIMPLE_FORMAT = "MMM dd, yyyy"
        const val SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }
}
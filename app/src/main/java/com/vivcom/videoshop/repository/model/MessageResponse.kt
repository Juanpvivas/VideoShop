package com.vivcom.videoshop.repository.model

class MessageResponse() : BaseModel() {
    var code: Int? = null
    var message: String? = null

    constructor(code: Int, message: String?) : this() {
        this.code = code
        this.message = message
    }
    /*var message: String?=null*/
}
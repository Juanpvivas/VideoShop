package com.vivcom.videoshop.repository.persistence.database

import com.vivcom.videoshop.repository.model.MessageResponse

abstract class BaseResponse<ResultType> {

    abstract fun okResult(resultType: ResultType?)

    abstract fun failResult(msg: MessageResponse? = null)
}
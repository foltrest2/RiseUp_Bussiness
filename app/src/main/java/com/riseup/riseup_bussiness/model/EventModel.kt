package com.riseup.riseup_bussiness.model

import java.io.Serializable
import java.util.*

data class EventModel (
    val id:String = "",
    var date: Date? = null,
    var posterID:String = "",
    var posterURL:String? = null
) : Serializable

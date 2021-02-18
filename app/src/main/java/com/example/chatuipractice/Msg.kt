package com.example.chatuipractice

import java.util.*

class Msg(val content: String, val type: Int, val msgTime: GregorianCalendar) {
    companion object
    {
        const val TYPE_SENT = 0
        const val TYPE_RECEIVED = 1
    }
}
package com.erlangshen.mvp.model

class MessageEvent<T> {
    var messageObj: T? = null//消息对象
    var messageStatus: String? = null//消息状态

    class ViewHeight {
        var height: Int = 0
    }
}
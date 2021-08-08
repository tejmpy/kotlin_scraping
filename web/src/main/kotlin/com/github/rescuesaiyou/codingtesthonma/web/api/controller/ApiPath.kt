package com.github.rescuesaiyou.codingtesthonma.web.api.controller

object ApiPath {
    private const val base = "/api"

    const val user = "$base/user"
    object User {
        const val getList = "$user/list"

        const val getDetail = "$user/{user_id}"

        const val save = user
    }

    const val notification = "$base/notification"
    object Notification {
        const val save = notification
    }

    const val salesStatus = "$base/sales_status"
    object SalesStatus {
        const val getListByUser = "$salesStatus/list/{user_id}"
    }

}

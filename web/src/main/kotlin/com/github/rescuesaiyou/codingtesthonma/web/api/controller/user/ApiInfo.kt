package com.github.rescuesaiyou.codingtesthonma.web.api.controller.user

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.User
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserName

object ApiInfo {

    data class GetList(
        val users: List<User>
    )

    object UserDetail {
        data class ResponseBody(
            val id: UserId,
            val name: UserName
        )
        data class RequestBody(
            val name: UserName
        )
    }
}

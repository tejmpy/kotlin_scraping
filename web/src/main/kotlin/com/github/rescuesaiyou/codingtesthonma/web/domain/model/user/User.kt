package com.github.rescuesaiyou.codingtesthonma.web.domain.model.user

import javax.validation.Valid

class User(
    @field: Valid val id: UserId,
    @field: Valid val name: UserName
) {

    companion object {
        fun register(
            name: UserName
        ): User {
            val id = UserId.generate()
            return User(id, name)
        }
    }
}

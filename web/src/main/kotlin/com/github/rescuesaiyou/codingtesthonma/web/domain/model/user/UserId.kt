package com.github.rescuesaiyou.codingtesthonma.web.domain.model.user

import java.util.UUID

data class UserId(val value: UUID) {
    companion object {
        fun generate(): UserId =
            UserId(UUID.randomUUID())
    }
}

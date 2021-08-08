package com.github.rescuesaiyou.codingtesthonma.web.domain.model.user

interface UserRepository {
    fun findAll(): List<User>

    fun find(id: UserId): User

    fun save(user: User)
}

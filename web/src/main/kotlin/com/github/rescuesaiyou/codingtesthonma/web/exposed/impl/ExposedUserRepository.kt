package com.github.rescuesaiyou.codingtesthonma.web.exposed.impl

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.User
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserRepository
import com.github.rescuesaiyou.codingtesthonma.web.exposed.table.UserTable
import com.github.rescuesaiyou.codingtesthonma.web.exposed.table.upsert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ExposedUserRepository : UserRepository {
    override fun findAll(): List<User> {
        val query = UserTable.selectAll()
        return query.map(UserTable::extract)
    }

    override fun find(id: UserId): User {
        val query = UserTable.select { UserTable.id eq id.value }
        return query.map(UserTable::extract).firstOrNull() ?: throw RuntimeException()
    }

    override fun save(user: User) {
        UserTable.upsert({
            UserTable.id eq user.id.value
        }) {
            it[id] = user.id.value
            it[name] = user.name.value
        }
    }
}

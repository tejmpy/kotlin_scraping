package com.github.rescuesaiyou.codingtesthonma.web.exposed.table

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.User
import com.github.rescuesaiyou.codingtesthonma.web.exposed.getAs
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UserTable : Table("user") {
    val id = uuid("id")

    val name = varchar("name", 100)

    override val primaryKey = PrimaryKey(id)

    fun extract(row: ResultRow): User =
        User(
            id = row.getAs(id),
            name = row.getAs(name)
        )
}

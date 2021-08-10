package com.github.rescuesaiyou.codingtesthonma.web.exposed.table

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatus
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.Status
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date

object SalesStatusTable : Table("sales_status") {
    val calendarDate = date("calendar_date")

    val tdlStatus = enumerationByName("tdl_status", 15, Status::class)

    val tdsStatus = enumerationByName("tds_status", 15, Status::class)

    override val primaryKey = PrimaryKey(calendarDate)

    fun extract(row: ResultRow): SalesStatus =
        SalesStatus(
            date = row[calendarDate],
            tdlStatus = row[tdlStatus],
            tdsStatus = row[tdsStatus]
        )
}

package com.github.rescuesaiyou.codingtesthonma.web.exposed.impl

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatus
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatusRepository
import com.github.rescuesaiyou.codingtesthonma.web.exposed.table.SalesStatusTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDate

class ExposedSalesStatusRepository : SalesStatusRepository {
    override fun findAll(): List<SalesStatus> {
        val query = SalesStatusTable.selectAll()
        return query.map(SalesStatusTable::extract)
    }

    override fun findBy(dates: List<LocalDate>): List<SalesStatus> {
        val query = SalesStatusTable.select { SalesStatusTable.calendarDate inList dates }
        return query.map(SalesStatusTable::extract)
    }

    override fun save(salesStatuses: List<SalesStatus>) {
        // 古い販売状況を削除
        SalesStatusTable.deleteWhere {
            SalesStatusTable.calendarDate inList salesStatuses.map { it.date }
        }
        salesStatuses.map { salesStatus ->
            SalesStatusTable.insert {
                it[calendarDate] = salesStatus.date
                it[tdlStatus] = salesStatus.tdlStatus
                it[tdsStatus] = salesStatus.tdsStatus
            }
        }
    }
}

package com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus

import java.time.LocalDate

interface SalesStatusRepository {
    fun findAll(): List<SalesStatus>

    fun findBy(dates: List<LocalDate>): List<SalesStatus>

    fun save(salesStatuses: List<SalesStatus>)
}

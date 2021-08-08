package com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus

import java.time.LocalDate
import java.time.YearMonth
import javax.validation.Valid

class SalesStatus(
    @field: Valid val date: LocalDate,
    @field: Valid val tdlStatus: Status,
    @field: Valid val tdsStatus: Status,
) {

    companion object {
        private val regex = Regex("""(\d{1,2})""")

        fun dateFrom(yearMonth: YearMonth, str: String): LocalDate? {
            return regex.find(str)?.let {
                val date = it.value.toInt()
                LocalDate.of(
                    yearMonth.year, yearMonth.month, date
                )
            }
        }
    }
}

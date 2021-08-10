package com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus

import com.github.rescuesaiyou.codingtesthonma.web.task.DataAttr
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.Valid

class SalesStatus(
    @field: Valid val date: LocalDate,
    @field: Valid val tdlStatus: Status,
    @field: Valid val tdsStatus: Status,
) {

    companion object {
        // 基本的にHPには3ヶ月分表示される
        const val monthLength = 3

        val dateSelector = DataAttr("ymd")
        const val TDL_STATUS_SELECTOR = ".tdl"
        const val TDS_STATUS_SELECTOR = ".tds"

        private val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

        fun dateFrom(str: String): LocalDate? {
            if (str.isEmpty()) return null
            return LocalDate.parse(str, dateFormatter)
        }
    }
}

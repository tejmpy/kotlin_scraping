package com.github.rescuesaiyou.codingtesthonma.web.api.controller.salestatus

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.Status
import java.time.LocalDate

object ApiInfo {

    data class GetList(
        val salesStatuses: List<SalesStatusDetail.ResponseBody>
    )

    object SalesStatusDetail {
        data class ResponseBody(
            val date: LocalDate,
            val tdlStatus: Status,
            val tdsStatus: Status
        )
    }
}

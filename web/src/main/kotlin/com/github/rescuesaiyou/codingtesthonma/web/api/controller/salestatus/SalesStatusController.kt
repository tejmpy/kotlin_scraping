package com.github.rescuesaiyou.codingtesthonma.web.api.controller.salestatus

import com.github.rescuesaiyou.codingtesthonma.web.api.controller.ApiPath
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.service.salesstatus.SalesStatusService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class SalesStatusController(
    @Value("\${tdr.url}") private val tdrUrl: String,
    private val salesStatusService: SalesStatusService
) {

    @GetMapping(ApiPath.SalesStatus.getListByUser)
    fun getListByUser(
        @ModelAttribute userId: UserId
    ): ApiInfo.GetList {
        val salesStatuses = salesStatusService.getBy(userId)

        return ApiInfo.GetList(
            salesStatuses.map {
                ApiInfo.SalesStatusDetail.ResponseBody(
                    date = it.date,
                    tdlStatus = it.tdlStatus,
                    tdsStatus = it.tdsStatus
                )
            }
        )
    }

    @ModelAttribute
    fun getUserIdFromPathVariable(
        @PathVariable("user_id", required = false) userId: UUID?
    ): UserId? = userId?.let { UserId(userId) }
}

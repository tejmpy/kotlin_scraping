package com.github.rescuesaiyou.codingtesthonma.web.api.controller.user

import com.github.rescuesaiyou.codingtesthonma.web.api.controller.ApiPath
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.User
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping(ApiPath.User.getList)
    fun getList(): ResponseEntity<ApiInfo.GetList> {
        return ResponseEntity.ok().body(
            ApiInfo.GetList(
                userService.getList()
            )
        )
    }

    @GetMapping(ApiPath.User.getDetail)
    fun getDetail(
        @ModelAttribute userId: UserId
    ): ResponseEntity<ApiInfo.UserDetail.ResponseBody> {
        val user = userService.getBy(userId)
        return ResponseEntity.ok().body(
            ApiInfo.UserDetail.ResponseBody(
                id = user.id,
                name = user.name
            )
        )
    }

    @PostMapping(ApiPath.User.save)
    fun register(
        @RequestBody @Valid body: ApiInfo.UserDetail.RequestBody
    ): ResponseEntity<ApiInfo.UserDetail.ResponseBody> {
        val user = User.register(body.name)
        userService.save(user)
        return ResponseEntity.ok().body(
            ApiInfo.UserDetail.ResponseBody(
                id = user.id,
                name = user.name
            )
        )
    }

    @ModelAttribute
    fun getUserIdFromPathVariable(
        @PathVariable("user_id", required = false) userId: UUID?
    ): UserId? = userId?.let { UserId(userId) }
}

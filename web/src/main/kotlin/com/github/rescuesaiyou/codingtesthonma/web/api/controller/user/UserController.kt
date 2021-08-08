package com.github.rescuesaiyou.codingtesthonma.web.api.controller.user

import com.github.rescuesaiyou.codingtesthonma.web.api.controller.ApiPath
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.User
import com.github.rescuesaiyou.codingtesthonma.web.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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

    @PostMapping(ApiPath.User.save)
    fun register(
        @RequestBody @Valid body: ApiInfo.UserInfo.RequestBody
    ): ResponseEntity<ApiInfo.UserInfo.ResponseBody> {
        val user = User.register(body.name)
        userService.save(user)
        return ResponseEntity.ok().body(
            ApiInfo.UserInfo.ResponseBody(
                id = user.id,
                name = user.name
            )
        )
    }
}

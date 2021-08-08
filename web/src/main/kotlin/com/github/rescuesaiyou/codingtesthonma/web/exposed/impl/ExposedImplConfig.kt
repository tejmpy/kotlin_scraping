package com.github.rescuesaiyou.codingtesthonma.web.exposed.impl

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserRepository
import org.springframework.context.annotation.Bean

class ExposedImplConfig {

    @Bean
    fun userRepository(): UserRepository =
        ExposedUserRepository()
}

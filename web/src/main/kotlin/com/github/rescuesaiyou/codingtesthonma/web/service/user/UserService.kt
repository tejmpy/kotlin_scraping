package com.github.rescuesaiyou.codingtesthonma.web.service.user

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.User
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun getList(): List<User> {
        return userRepository.findAll()
    }

    @Transactional(readOnly = false)
    fun save(user: User): User {
        userRepository.save(user)
        return user
    }
}

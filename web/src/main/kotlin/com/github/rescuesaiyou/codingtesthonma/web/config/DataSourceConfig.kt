package com.github.rescuesaiyou.codingtesthonma.web.config

import org.jetbrains.exposed.sql.Database
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean

@ConfigurationProperties(prefix = "spring.datasource")
@ConstructorBinding
class DataSourceConfig(
    val driverClassName: String,
    val url: String,
    val username: String,
    val password: String
) {

    @Bean(name = ["restApiDbDatabase"])
    fun createConnect(): Database {
        return Database.connect(
            url = url,
            driver = driverClassName,
            user = username,
            password = password
        )
    }
}

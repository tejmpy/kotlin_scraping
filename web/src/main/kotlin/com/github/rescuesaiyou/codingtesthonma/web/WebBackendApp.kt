package com.github.rescuesaiyou.codingtesthonma.web

import com.github.rescuesaiyou.codingtesthonma.web.exposed.impl.ExposedImplConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@Import(
    ExposedImplConfig::class,
)
class WebBackendApp

fun main(args: Array<String>) {
    SpringApplication.run(WebBackendApp::class.java, *args)
}

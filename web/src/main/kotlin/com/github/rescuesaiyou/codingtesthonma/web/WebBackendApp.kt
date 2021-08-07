package com.github.rescuesaiyou.codingtesthonma.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WebBackendApp

fun main(args: Array<String>) {
    SpringApplication.run(WebBackendApp::class.java, *args)
}

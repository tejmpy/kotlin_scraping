package com.github.rescuesaiyou.codingtesthonma.web.task

class DataAttr(
    val name: String
) {

    fun selector(): String = "data-$name"
    fun selectorWithBrackets(): String = "[data-$name]"
}

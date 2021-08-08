package com.github.rescuesaiyou.codingtesthonma.web.exposed

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * ResultRowから任意のカラムを取り出して値オブジェクトを生成する
 * コンストラクタ引数が1つのみの値ブジェクトにのみ対応
 */
inline fun <T, U, reified V : Any> ResultRow.getAs(
    column: Column<T>,
    valueConverter: (T) -> U
): V {
    val value: U = valueConverter(get(column))
    val constructor = V::class.findAcceptableConstructor(value) ?: throw IllegalArgumentException("${V::class}")
    return constructor.call(value)
}

inline fun <T, reified V : Any> ResultRow.getAs(column: Column<T>): V = getAs(column) { it }

fun <T, V : Any> KClass<V>.findAcceptableConstructor(value: T): KFunction<V>? =
    constructors.find {
        it.parameters.size == 1 && (it.parameters.first().type.classifier as KClass<*>).isInstance(value)
    }

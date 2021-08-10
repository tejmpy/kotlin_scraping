package com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus

import org.jsoup.nodes.Element

enum class Status {
    AVAILABLE,
    FEW_REMAINING,
    UNAVAILABLE,
    CLOSED;

    companion object {
        fun valueFrom(element: Element): Status {
            val classNames = element.classNames()
            return if (classNames.contains("is-close")) CLOSED
            else if (classNames.contains("is-none")) UNAVAILABLE
            else if (classNames.contains("is-fes")) FEW_REMAINING
            else AVAILABLE
        }
    }
}

package com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus

import org.jsoup.nodes.Element

enum class Status {
    AVAILABLE,
    FEW_REMAINING,
    UNAVAILABLE;

    companion object {
        fun valueFrom(element: Element): Status {
            val classNames = element.classNames()
            return if (classNames.contains("is-none") || classNames.contains("is-close")) UNAVAILABLE
            else if (classNames.contains("is-fes")) FEW_REMAINING
            else AVAILABLE
        }
    }
}

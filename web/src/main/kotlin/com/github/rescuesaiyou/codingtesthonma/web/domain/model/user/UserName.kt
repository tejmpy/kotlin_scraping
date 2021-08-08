package com.github.rescuesaiyou.codingtesthonma.web.domain.model.user

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserName(
    @field: [NotBlank Size(max = 100)]
    val value: String
)

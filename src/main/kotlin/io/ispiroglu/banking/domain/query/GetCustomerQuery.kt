package io.ispiroglu.banking.domain.query

import java.util.UUID

data class GetCustomerQuery(
    val userId: UUID
)

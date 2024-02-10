package io.ispiroglu.banking.domain.event

import java.util.UUID

data class EmailChangedEvent(
    val userId: UUID,
    val email: String
)

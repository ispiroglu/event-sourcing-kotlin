package io.ispiroglu.banking.domain.event

import io.ispiroglu.banking.domain.persistence.CustomerView
import java.util.UUID

data class CustomerCreatedEvent(
    val id: UUID,
    val username: String,
    val firstName: String,
    val lastName: String
)

fun CustomerCreatedEvent.toCustomerView(): CustomerView =
    CustomerView(id, username, firstName, lastName)
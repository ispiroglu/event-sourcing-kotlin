package io.ispiroglu.banking.controller.dto

import io.ispiroglu.banking.domain.command.CreateCustomerCommand
import java.util.UUID

data class CreateCustomerRequest(
    val username: String,
    val firstName: String,
    val lastName: String,
    var email: String? = null,
)

fun CreateCustomerRequest.toCreateCustomerCommand(id: UUID) =
    CreateCustomerCommand(id, username, firstName, lastName)

package io.ispiroglu.banking.domain.command

import io.ispiroglu.banking.domain.event.CustomerCreatedEvent
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.UUID

data class CreateCustomerCommand(

    @TargetAggregateIdentifier
    val id: UUID,
    val username: String,
    val firstName: String,
    val lastName: String
)

fun CreateCustomerCommand.toUserCreatedEvent(): CustomerCreatedEvent =
    CustomerCreatedEvent(id, username, firstName, lastName)


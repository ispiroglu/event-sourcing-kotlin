package io.ispiroglu.banking.domain.command

import io.ispiroglu.banking.domain.event.EmailChangedEvent
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.UUID


class ChangeEmailCommand(
    @TargetAggregateIdentifier
    val userId: UUID,
    val email: String
)

fun ChangeEmailCommand.toEmailAddedEvent() = EmailChangedEvent(userId, email)


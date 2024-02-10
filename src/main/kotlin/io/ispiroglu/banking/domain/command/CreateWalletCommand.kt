package io.ispiroglu.banking.domain.command

import io.ispiroglu.banking.domain.event.WalletCreatedEvent
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.UUID

data class CreateWalletCommand(

    @TargetAggregateIdentifier
    val userId: UUID,
    val balance: BigDecimal
)

fun CreateWalletCommand.toWalletCreatedEvent(): WalletCreatedEvent =
    WalletCreatedEvent(userId, balance)

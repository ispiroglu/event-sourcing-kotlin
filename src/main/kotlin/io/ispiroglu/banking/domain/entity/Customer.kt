package io.ispiroglu.banking.domain.entity

import io.ispiroglu.banking.domain.command.*
import io.ispiroglu.banking.domain.event.EmailChangedEvent
import io.ispiroglu.banking.domain.event.CustomerCreatedEvent
import io.ispiroglu.banking.domain.event.WalletCreatedEvent
import io.ispiroglu.banking.domain.vo.Wallet
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
class Customer() {

    @AggregateIdentifier
    private lateinit var id : UUID
    private lateinit var firstName: String
    private lateinit var lastName: String
    private var email: String? = null
    private var wallet: Wallet? = null

    @CommandHandler
    constructor(command: CreateCustomerCommand): this() {
        AggregateLifecycle.apply(command.toUserCreatedEvent())
    }

    @CommandHandler
    fun handle(command: CreateWalletCommand) {
        wallet?.let {
            throw IllegalStateException("Wallet already added to this user")
        } ?: run {
            AggregateLifecycle.apply(command.toWalletCreatedEvent())
        }
    }

    @CommandHandler
    fun handle(command: ChangeEmailCommand) {
        AggregateLifecycle.apply(command.toEmailAddedEvent())
    }

    @EventSourcingHandler
    fun apply(event: CustomerCreatedEvent) {
        event.let {
            id = it.id
            firstName = it.firstName
            lastName = it.lastName
        }
    }

    @EventSourcingHandler
    fun apply(event: WalletCreatedEvent) {
        wallet = Wallet(event.balance)
    }

    @EventSourcingHandler
    fun apply(event: EmailChangedEvent) {
        email = event.email
    }
}
package io.ispiroglu.banking.command

import io.ispiroglu.banking.domain.event.*
import io.ispiroglu.banking.domain.persistence.CustomerView
import io.ispiroglu.banking.query.CustomerViewRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@ProcessingGroup("customer-banking")
class CustomerCommandHandler (private val repository: CustomerViewRepository) {

    @EventHandler
    fun on(event: CustomerCreatedEvent) {
        event
            .toCustomerView()
            .run {
                repository.save(this)
            }
    }

    @EventHandler
    fun on(event: EmailChangedEvent) {
        getByIdOrThrowException(event.userId)
            .apply {
                email = event.email
            }
            .let {
                repository.save(it)
            }
    }

    @EventHandler
    fun on(event: WalletCreatedEvent) {
        getByIdOrThrowException(event.userId)
            .apply {
                wallet = event.toWallet()
            }
            .let {
                repository.save(it)
            }
    }

    private fun getByIdOrThrowException(id: UUID): CustomerView {
        return repository.findById(id)
            .orElseThrow { IllegalArgumentException("Cannot find customer with id $id") }
    }
}
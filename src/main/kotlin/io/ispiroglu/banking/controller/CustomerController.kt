package io.ispiroglu.banking.controller


import io.ispiroglu.banking.controller.dto.CreateCustomerRequest
import io.ispiroglu.banking.controller.dto.toCreateCustomerCommand
import io.ispiroglu.banking.domain.command.ChangeEmailCommand
import io.ispiroglu.banking.domain.command.CreateWalletCommand
import io.ispiroglu.banking.domain.query.GetCustomerQuery
import io.ispiroglu.banking.domain.persistence.CustomerView
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random


@RestController
@RequestMapping("/customer")
class CustomerController(
    private val commandGateway: ReactorCommandGateway,
    private val queryGateway: ReactorQueryGateway
) {

    @PostMapping
    // TODO: adding suspend here?
    suspend fun createCustomer(@RequestBody request: CreateCustomerRequest): Mono<Unit> =
        // Generating random Long here can be a bad practice
        request.toCreateCustomerCommand(UUID.randomUUID())
            .let {
                commandGateway.send(it)
            }

    @PostMapping("{id}/email")
    fun changeEmail(@PathVariable id: UUID, @RequestParam email: String): Mono<Unit> {
        val command = ChangeEmailCommand(id, email)
        return commandGateway.send(command)
    }

    @PostMapping("{id}/wallet")
    fun createWallet(@PathVariable id: UUID, @RequestParam(required = false) balance: BigDecimal = BigDecimal.ZERO): Mono<Unit> {
        return CreateWalletCommand(id, balance)
            .let {
                commandGateway.send(it)
            }
    }

    @GetMapping("{id}")
    fun getCustomer(@PathVariable id: UUID): Mono<CustomerView> {
        val query = GetCustomerQuery(id)
        return queryGateway.query(query, CustomerView::class.java)
    }
}

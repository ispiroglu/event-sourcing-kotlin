package io.ispiroglu.banking.query

import io.ispiroglu.banking.domain.persistence.CustomerView
import io.ispiroglu.banking.domain.query.GetCustomerQuery
import org.axonframework.config.ProcessingGroup
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("customer-banking")
class CustomerViewQueryHandler(private val repository: CustomerViewRepository) {

    @QueryHandler
    fun on(query: GetCustomerQuery): CustomerView {
        return repository.findById(query.userId)
            .orElseThrow { IllegalArgumentException("Cannot found customer with id: ${query.userId}") }
    }
}
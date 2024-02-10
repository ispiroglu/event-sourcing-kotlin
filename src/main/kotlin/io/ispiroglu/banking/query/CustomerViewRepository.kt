package io.ispiroglu.banking.query

import io.ispiroglu.banking.domain.persistence.CustomerView
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface CustomerViewRepository: MongoRepository<CustomerView, UUID>
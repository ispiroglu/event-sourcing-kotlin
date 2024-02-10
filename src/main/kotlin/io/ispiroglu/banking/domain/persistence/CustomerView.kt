package io.ispiroglu.banking.domain.persistence

import io.ispiroglu.banking.domain.vo.Wallet
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID


// TODO: Where to locate this?
@Document("customerViews")
data class CustomerView(

    @Id
    val id: UUID,
    val username: String,
    val firstName: String,
    val lastName: String,
    var email: String? = null,
    var wallet: Wallet? = null
)

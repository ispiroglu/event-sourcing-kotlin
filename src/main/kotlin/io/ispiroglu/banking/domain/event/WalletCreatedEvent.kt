package io.ispiroglu.banking.domain.event


import io.ispiroglu.banking.domain.vo.Wallet
import java.math.BigDecimal
import java.util.UUID

data class WalletCreatedEvent(
    val userId: UUID,
    val balance: BigDecimal
)

fun WalletCreatedEvent.toWallet(): Wallet = Wallet(balance)

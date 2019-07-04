package ru.amaev.budget.rest

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

class AddAccountRequest @JsonCreator constructor(val userId: String,
                                                 val currencyId: String)

class AddTransactionRequest @JsonCreator constructor(val amount: BigDecimal,
                                                     val accountId: String,
                                                     val userId: String)

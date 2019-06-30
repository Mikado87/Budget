package ru.amaev.budget.data

import java.math.BigDecimal


class Account(val accountId: Long,
              val currencyId: Currency,
              val userAccount: UserAccount,
              val sharedFor: List<UserAccount>,
              val archived: Boolean)

class Category(val categoryName: String,
               val id: Long,
               val name: String,
               val archived: Boolean)

class Currency(val currencyId: Long,
               val currencyName: String,
               val archived: Boolean)

class Transaction(val accountId: Long,
                  val currencyId: Long,
                  val amount: BigDecimal,
                  val archived: Boolean)

class UserAccount(val id: Long,
                  val firstName: String,
                  val lastName: String,
                  val middleName: String,
                  val archived: Boolean)
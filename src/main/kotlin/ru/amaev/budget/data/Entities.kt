package ru.amaev.budget.data

import java.math.BigDecimal

@Entity
class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val accountId: Long,
        val currencyId: Currency,
        val userAccount: UserAccount,
        val sharedFor: List<UserAccount>,
        val archived: Boolean)

class Category(val categoryName: String,
               @Id
               @GeneratedValue(strategy = GenerationType.AUTO)
               val id: Long,
               val name: String,
               val archived: Boolean)

class Currency(@Id
               @GeneratedValue(strategy = GenerationType.AUTO)
               val currencyId: Long,
               val currencyName: String,
               val archived: Boolean)

class Transaction(@Id
                  @GeneratedValue(strategy = GenerationType.AUTO) val accountId: Long,
                  val currencyId: Long,
                  val amount: BigDecimal,
                  val archived: Boolean)

class UserAccount(@Id
                  @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
                  val firstName: String,
                  val lastName: String,
                  val middleName: String,
                  val archived: Boolean)
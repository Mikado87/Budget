package ru.amaev.budget.data

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*


@Entity
class Account @JsonCreator constructor(@ManyToOne(fetch = FetchType.LAZY)
                                       @JoinColumn(name = "created_by") val userAccount: UserAccount,
//                                       @ManyToMany(fetch = FetchType.LAZY)
//                                       @JoinTable(name = "user_account", joinColumns = arrayOf(JoinColumn(name = "shared_for")))
//                                       val sharedFor: List<UserAccount>,
                                       @ManyToOne
                                       @JoinColumn(name = "currency_id") val currency: Currency)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
class Category @JsonCreator constructor(@Column(name = "category_name") val categoryName: String)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
class Currency @JsonCreator constructor(@Column(name = "currency_name") val currencyName: String)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
class Transaction @JsonCreator constructor(@Column(name = "amount") val amount: BigDecimal,
                                           @ManyToOne(fetch = FetchType.LAZY)
                                           @JoinColumn(name = "account_id") val account: Account,
                                           @ManyToOne(fetch = FetchType.EAGER)
                                           @JoinColumn(name = "user_account_id") val userAccount: UserAccount)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
class UserAccount @JsonCreator constructor(@Column(name = "first_name") val firstName: String,
                                           @Column(name = "last_name") val lastName: String,
                                           @Column(name = "middle_name") val middleName: String)
    : AbstractJpaEntity<Long>(), Serializable
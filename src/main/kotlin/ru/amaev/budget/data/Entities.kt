package ru.amaev.budget.data

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*


@Entity
data class Account @JsonCreator constructor(@ManyToOne(fetch = FetchType.LAZY)
                                            @JoinColumn(name = "created_by") var userAccount: UserAccount?,
//                                       @ManyToMany(fetch = FetchType.LAZY)
//                                       @JoinTable(name = "user_account", joinColumns = arrayOf(JoinColumn(name = "shared_for")))
//                                       val sharedFor: List<UserAccount>,
                                            @ManyToOne
                                            @JoinColumn(name = "currency_id") var currency: Currency?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
data class Category @JsonCreator constructor(@Column(name = "category_name") var categoryName: String?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
data class Currency @JsonCreator constructor(@Column(name = "currency_name") var currencyName: String?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
data class Transaction @JsonCreator constructor(@Column(name = "amount") var amount: BigDecimal?,
                                                @ManyToOne(fetch = FetchType.LAZY)
                                                @JoinColumn(name = "account_id") var account: Account?,
                                                @ManyToOne(fetch = FetchType.EAGER)
                                                @JoinColumn(name = "user_account_id") var userAccount: UserAccount?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
class UserAccount @JsonCreator constructor(@Column(name = "first_name") var firstName: String?,
                                           @Column(name = "last_name") var lastName: String?,
                                           @Column(name = "middle_name") var middleName: String?)
    : AbstractJpaEntity<Long>(), Serializable
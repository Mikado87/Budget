package ru.amaev.budget.data

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*


@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "account_seq", allocationSize = 1)
data class Account @JsonCreator constructor(@ManyToOne(fetch = FetchType.LAZY)
                                            @JoinColumn(name = "created_by", referencedColumnName = "id") var userAccount: User?,
//                                       @ManyToMany(fetch = FetchType.LAZY)
//                                       @JoinTable(name = "user_account", joinColumns = arrayOf(JoinColumn(name = "shared_for")))
//                                       val sharedFor: List<User>,
                                            @ManyToOne
                                            @JoinColumn(name = "currency_id", referencedColumnName = "id") var currency: Currency?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "category_seq", allocationSize = 1)
data class Category @JsonCreator constructor(@Column(name = "category_name") var categoryName: String?,
                                             @Column(name = "type") var type: CategoryType?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "currency_seq", allocationSize = 1)
data class Currency @JsonCreator constructor(@Column(name = "currency_name") var currencyName: String?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "transaction_seq", allocationSize = 1)
data class Transaction @JsonCreator constructor(@Column(name = "amount") var amount: BigDecimal?,
                                                @ManyToOne(fetch = FetchType.LAZY)
                                                @JoinColumn(name = "account_id") var account: Account?,
                                                @ManyToOne(fetch = FetchType.EAGER)
                                                @JoinColumn(name = "user_account_id") var userAccount: User?)
    : AbstractJpaEntity<Long>(), Serializable

@Entity(name = "usr")
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "usr_seq", allocationSize = 1)
data class User @JsonCreator constructor(@Column(name = "first_name") var firstName: String?,
                                         @Column(name = "last_name") var lastName: String?,
                                         @Column(name = "middle_name") var middleName: String?,
                                         @Column(name = "role") var role: Role?)
    : AbstractJpaEntity<Long>(), Serializable

enum class Role { USER, ADMIN }

enum class CategoryType { INCOME, EXPENSE }
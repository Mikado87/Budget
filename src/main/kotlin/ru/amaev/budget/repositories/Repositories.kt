package ru.amaev.budget.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.amaev.budget.data.*

interface UserRepository : CrudRepository<User, Long> {
    @Query("from User u WHERE u.login = :login")
    fun findByLogin(@Param("login") login: String): User?
}

interface AccountRepository : CrudRepository<Account, Long>

interface CategoryRepository : CrudRepository<Category, Long>

interface CurrencyRepository : CrudRepository<Currency, Long>

interface TransactionRepository : CrudRepository<Transaction, Long>


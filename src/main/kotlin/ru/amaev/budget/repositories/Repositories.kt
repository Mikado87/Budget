package ru.amaev.budget.repositories

import org.springframework.data.repository.CrudRepository
import ru.amaev.budget.data.*

interface UserRepository : CrudRepository<User, Long>

interface AccountRepository : CrudRepository<Account, Long>

interface CategoryRepository : CrudRepository<Category, Long>

interface CurrencyRepository : CrudRepository<Currency, Long>

interface TransactionRepository : CrudRepository<Transaction, Long>


package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.Account
import ru.amaev.budget.errors.DataNotFound
import ru.amaev.budget.repositories.AccountRepository
import ru.amaev.budget.repositories.CurrencyRepository
import ru.amaev.budget.repositories.UserRepository
import ru.amaev.budget.rest.AddAccountRequest


@RestController
@Api
@RequestMapping("/accounts")
class AccountController {

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var currencyRepository: CurrencyRepository

    // TODO Disable after start
    @GetMapping
    fun getAllAccounts(): Iterable<Account> {
        return accountRepository.findAll()
    }

    @GetMapping("get/{id}")
    fun getAccount(@PathVariable id: String): Account = accountRepository.findByIdOrNull(id.toLong())
            ?: throw DataNotFound()

    @PutMapping("add")
    fun addAccount(@RequestBody addAccountRequest: AddAccountRequest) {
        val account = Account(
                userRepository.findByIdOrNull(addAccountRequest.userId.toLong()) ?: throw DataNotFound()
                , currencyRepository.findByIdOrNull(addAccountRequest.currencyId.toLong()) ?: throw DataNotFound())
        accountRepository.save(account)
    }

    @DeleteMapping("remove/{id}")
    fun removeAccount(@PathVariable id: String) {
        val account = accountRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        account.setArchived()
        accountRepository.save(account)
    }

    @PatchMapping("update")
    fun updateAccount(@RequestBody account: Account) {
        if (account.getId() != null) {
            val accountFromDB = accountRepository.findByIdOrNull(account.getId()!!.toLong()) ?: throw DataNotFound()
            if (account.userAccount != null) accountFromDB.userAccount = account.userAccount
            if (account.currency != null) accountFromDB.currency = account.currency
            accountRepository.save(accountFromDB)
        } else throw DataNotFound()
    }
}
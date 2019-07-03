package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.Currency
import ru.amaev.budget.errors.DataNotFound
import ru.amaev.budget.repositories.CurrencyRepository


@RestController
@Api
@RequestMapping("/currencies")
class CurrencyAccountController {

    @Autowired
    lateinit var currencyRepository: CurrencyRepository

    // TODO Disable after start
    @GetMapping
    fun getAllCurrencies(): Iterable<Currency> {
        return currencyRepository.findAll()
    }

    @GetMapping("get/{id}")
    fun getCurrency(@PathVariable id: String): Currency = currencyRepository.findByIdOrNull(id.toLong())
            ?: throw DataNotFound()

    @PutMapping("add")
    fun addCurrency(@RequestBody currency: Currency) {
        currencyRepository.save(currency)
    }

    @DeleteMapping("remove/{id}")
    fun removeCurrency(@PathVariable id: String) {
        val userAccount = currencyRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        userAccount.setArchived()
        currencyRepository.save(userAccount)
    }

    @PatchMapping("update")
    fun updateCurrency(@RequestBody currency: Currency) {
        if (currency.getId() != null) {
            val currencyFromDB = currencyRepository.findByIdOrNull(currency.getId()!!.toLong()) ?: throw DataNotFound()
            if (currency.currencyName != null) currencyFromDB.currencyName = currency.currencyName
            currencyRepository.save(currencyFromDB)
        } else throw DataNotFound()
    }
}
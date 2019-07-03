package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.Transaction
import ru.amaev.budget.errors.DataNotFound
import ru.amaev.budget.repositories.TransactionRepository


@RestController
@Api
@RequestMapping("/transactions")
class TransactionController {

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    // TODO Disable after start
    @GetMapping
    fun getAllTransactions(): Iterable<Transaction> {
        return transactionRepository.findAll()
    }

    @GetMapping("get/{id}")
    fun getTransaction(@PathVariable id: String): Transaction = transactionRepository.findByIdOrNull(id.toLong())
            ?: throw DataNotFound()

    @PutMapping("add")
    fun addTransaction(@RequestBody transaction: Transaction) {
        transactionRepository.save(transaction)
    }

    @DeleteMapping("remove/{id}")
    fun removeTransaction(@PathVariable id: String) {
        val transaction = transactionRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        transaction.setArchived()
        transactionRepository.save(transaction)
    }

    @PatchMapping("update")
    fun updateTransaction(@RequestBody transaction: Transaction) {
        if (transaction.getId() != null) {
            val transactionFromDB = transactionRepository.findByIdOrNull(transaction.getId()!!.toLong())
                    ?: throw DataNotFound()
            if (transaction.amount != null) transactionFromDB.amount = transaction.amount
            if (transaction.account != null) transactionFromDB.account = transaction.account
            if (transaction.userAccount != null) transactionFromDB.userAccount = transaction.userAccount
            transactionRepository.save(transactionFromDB)
        } else throw DataNotFound()
    }
}
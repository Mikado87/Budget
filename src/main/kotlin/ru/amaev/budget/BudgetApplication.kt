package ru.amaev.budget

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BudgetApplication

fun main(args: Array<String>) {
	runApplication<BudgetApplication>(*args)
}

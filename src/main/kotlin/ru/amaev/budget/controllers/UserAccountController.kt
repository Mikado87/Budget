package ru.amaev.budget.controllers

import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.UserAccount
import ru.amaev.budget.errors.DataNotFound

@RestController
@RequestMapping("/users")
class UserAccountController {

    private var nextIndex = 4L
    var userAccountMap = mutableMapOf(1L to UserAccount(1L, "Иванов", "Николай", "Петрович", false),
            2L to UserAccount(2L, "Петров", "Геннадий", "Юсупович", false),
            3L to UserAccount(3L, "Жуков", "Георгий", "Нассонович", true))


    fun getTemp(): List<UserAccount> {
        return userAccountMap.values.toList()
    }

    @GetMapping
    fun getAllUsers(): List<UserAccount> {
        return getTemp()
    }


    @GetMapping("get/{id}")
    fun getUser(@PathVariable id: String): UserAccount {
        return userAccountMap[id.toLong()] ?: throw DataNotFound()
    }

    @PutMapping("add")
    fun addUser(@PathVariable id: String,
                @PathVariable firstName: String,
                @PathVariable middleName: String,
                @PathVariable lastName: String) {
        userAccountMap.plus(Pair(nextIndex, UserAccount(id.toLong(), firstName, lastName, middleName, false)))
        nextIndex++
    }

    @DeleteMapping("remove/{id}")
    fun removeUser(@PathVariable id: String) {
        if (userAccountMap.containsKey(id.toLong())) {
            userAccountMap.remove(id.toLong())
        } else {
            throw DataNotFound()
        }
    }

    @PatchMapping("update/{id}")
    fun updateUserAccount(@PathVariable id: String,
                          @PathVariable firstName: String,
                          @PathVariable middleName: String,
                          @PathVariable lastName: String,
                          @PathVariable archived: Boolean) {
    }
}
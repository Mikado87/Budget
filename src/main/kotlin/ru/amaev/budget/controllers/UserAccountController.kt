package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.UserAccount
import ru.amaev.budget.errors.DataNotFound
import ru.amaev.budget.repositories.UserAccountRepository


@RestController
@Api
@RequestMapping("/users")
class UserAccountController {

    @Autowired
    lateinit var userRepository: UserAccountRepository

    private var nextIndex = 4L
//    var userAccountMap = mutableMapOf(1L to UserAccount(1L, "Иванов", "Николай", "Петрович", false),
//            2L to UserAccount(2L, "Петров", "Геннадий", "Юсупович", false),
//            3L to UserAccount(3L, "Жуков", "Георгий", "Нассонович", true))


    fun getTemp(): Iterable<UserAccount> {
        return userRepository.findAll()
    }

    @GetMapping
    fun getAllUsers(): Iterable<UserAccount> {
        return userRepository.findAll()
    }


    @GetMapping("get/{id}")
    fun getUser(@PathVariable id: String): UserAccount {


        return  userRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
    }

    @PutMapping("add")
    fun addUser(@RequestBody user: UserAccount) {
        println("request = [${user}]")


        userRepository.save(user)
    }

    @DeleteMapping("remove/{id}")
    fun removeUser(@PathVariable id: String) {
        val userAccount = userRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        userAccount.setArchived()
        userRepository.save(userAccount)
    }

    @PatchMapping("update/")
    fun updateUserAccount(@RequestParam id: String,
                          @PathVariable firstName: String,
                          @PathVariable middleName: String,
                          @PathVariable lastName: String,
                          @PathVariable archived: Boolean) {
    }
}
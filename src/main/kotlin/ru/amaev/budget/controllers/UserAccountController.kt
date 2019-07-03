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

    // TODO Disable after start
    @GetMapping
    fun getAllUsers(): Iterable<UserAccount> {
        return userRepository.findAll()
    }

    @GetMapping("get/{id}")
    fun getUser(@PathVariable id: String): UserAccount = userRepository.findByIdOrNull(id.toLong())
            ?: throw DataNotFound()

    @PutMapping("add")
    fun addUser(@RequestBody user: UserAccount) {
        userRepository.save(user)
    }

    @DeleteMapping("remove/{id}")
    fun removeUser(@PathVariable id: String) {
        val userAccount = userRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        userAccount.setArchived()
        userRepository.save(userAccount)
    }

    @PatchMapping("update")
    fun updateUserAccount(@RequestBody user: UserAccount) {
        if (user.getId() != null) {
            val userAccount = userRepository.findByIdOrNull(user.getId()!!.toLong()) ?: throw DataNotFound()
            if (user.firstName != null) userAccount.firstName = user.firstName
            if (user.lastName != null) userAccount.lastName = user.lastName
            if (user.middleName != null) userAccount.middleName = user.middleName
            userRepository.save(userAccount)
        } else throw DataNotFound()
    }
}
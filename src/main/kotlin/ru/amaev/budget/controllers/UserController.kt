package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.Role
import ru.amaev.budget.data.User
import ru.amaev.budget.errors.DataNotFound
import ru.amaev.budget.repositories.UserRepository


@RestController
@Api
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository

    // TODO Disable after start
    @GetMapping
    fun getAllUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    @GetMapping("get/{id}")
    fun getUser(@PathVariable id: String): User = userRepository.findByIdOrNull(id.toLong())
            ?: throw DataNotFound()

    @PutMapping("add")
    fun addUser(@RequestBody user: User) {
        if (user.role == null) user.role = Role.USER
        userRepository.save(user)
    }

    @DeleteMapping("remove/{id}")
    fun removeUser(@PathVariable id: String) {
        val userAccount = userRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        userAccount.setArchived()
        userRepository.save(userAccount)
    }

    @PatchMapping("update")
    fun updateUser(@RequestBody user: User) {
        if (user.getId() != null) {
            val userAccount = userRepository.findByIdOrNull(user.getId()!!.toLong()) ?: throw DataNotFound()
            if (user.firstName != null) userAccount.firstName = user.firstName
            if (user.lastName != null) userAccount.lastName = user.lastName
            if (user.middleName != null) userAccount.middleName = user.middleName
            if (user.role != null) userAccount.role = user.role
            userRepository.save(userAccount)
        } else throw DataNotFound()
    }
}
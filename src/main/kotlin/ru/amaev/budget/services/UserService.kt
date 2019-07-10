package ru.amaev.budget.services

import ru.amaev.budget.data.User

interface UserService {

    abstract fun register(user: User): User

    abstract fun getAll(): List<User>

    abstract fun findByLogin(username: String): User

    abstract fun findById(id: Long): User

    abstract fun delete(id: Long)
}
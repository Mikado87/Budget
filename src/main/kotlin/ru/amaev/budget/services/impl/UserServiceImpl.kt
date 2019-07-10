package ru.amaev.budget.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.amaev.budget.data.User
import ru.amaev.budget.repositories.UserRepository
import ru.amaev.budget.services.UserService

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun register(user: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws(UsernameNotFoundException::class)
    override fun findByLogin(username: String): User =
            userRepository.findByLogin(username)
                    ?: throw UsernameNotFoundException("User with username: $username not found")

    override fun findById(id: Long): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
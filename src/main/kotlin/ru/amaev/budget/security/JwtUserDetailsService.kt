package ru.amaev.budget.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.amaev.budget.data.User
import ru.amaev.budget.security.jwt.JwtUserFactory
import ru.amaev.budget.services.UserService

@Service(value = "jwtUserDetailsService")
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userService: UserService

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userService.findByLogin(username)
        return JwtUserFactory.create(user)
    }
}

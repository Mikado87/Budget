package ru.amaev.budget.security.jwt

import org.springframework.security.core.authority.SimpleGrantedAuthority
import ru.amaev.budget.data.User


class JwtUserFactory {
    companion object {

        fun create(user: User): JwtUser {
            return JwtUser(
                    user.id!!,
                    user.login!!,
                    user.firstName!!,
                    user.lastName!!,
                    user.email!!,
                    user.password!!,
                    listOf(SimpleGrantedAuthority(user.role!!.name)),
                    !user.getArchived()
            )
        }
    }
}

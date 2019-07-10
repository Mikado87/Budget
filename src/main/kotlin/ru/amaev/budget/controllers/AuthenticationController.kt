package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.amaev.budget.security.jwt.JwtTokenProvider
import ru.amaev.budget.services.UserService
import java.util.*



/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@RestController
@Api
@RequestMapping("/auth1")
class AuthenticationController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var userService: UserService

    @PostMapping("login1")
    fun login(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<*> {
        try {
            if (!authRequest.isValid()) throw UsernameNotFoundException("Bad request")

//            val encodedPass = passwordEncoder.encode(authRequest.password!!)

            val login = authRequest.login
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(login, authRequest.password))
            val user = userService.findByLogin(login!!)

            val token = jwtTokenProvider.createToken(login, user.role!!)

            val response = HashMap<Any, Any>()
            response["username"] = login
            response["token"] = token

            return ResponseEntity.ok<Map<Any, Any>>(response)
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username or password")
        }
    }

    data class AuthenticationRequest(val login: String?, val password: String?) {
        fun isValid(): Boolean = (login != null && password != null)
    }
}

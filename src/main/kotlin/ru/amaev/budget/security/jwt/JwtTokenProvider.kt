package ru.amaev.budget.security.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import ru.amaev.budget.data.Role
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider {

    @Value("\${jwt.token.secret}")
    private var secret: String? = null

    @Value("\${jwt.token.expired}")
    private val validityInMilliseconds: Long = 0


    @Qualifier("jwtUserDetailsService")
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret!!.toByteArray())
    }

    fun createToken(username: String, role: Role): String {

        val claims = Jwts.claims().setSubject(username)
        claims["roles"] = role

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
                .compact()
    }

    @Throws(UsernameNotFoundException::class)
    fun getAuthentication(token: String): Authentication {
        val userDetails = this.userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)

            return !claims.body.expiration.before(Date())

        } catch (e: JwtException) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        } catch (e: IllegalArgumentException) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        }
    }

}

package ru.amaev.budget.security.jwt

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser(
        val id: Long,
        private val username: String,
        val firstname: String,
        val lastname: String,
        val email: String,
        private val password: String,
        private val authorities: Collection<GrantedAuthority>,
        private val enabled: Boolean
) : UserDetails {

    override fun getUsername(): String {
        return username
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}

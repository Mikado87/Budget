package ru.amaev.budget.security.jwt

import org.springframework.security.core.AuthenticationException

/**
 * Authetication exception for JwtAppDemo application.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

class JwtAuthenticationException : AuthenticationException {
    constructor(msg: String, t: Throwable) : super(msg, t) {}

    constructor(msg: String) : super(msg) {}
}

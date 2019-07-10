package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Api
@Controller
class WebController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping("/user")
    fun welcome(): String {
        return "user"
    }

    @GetMapping("/admin")
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/403")
    fun error403(): String {
        return "403"
    }
}
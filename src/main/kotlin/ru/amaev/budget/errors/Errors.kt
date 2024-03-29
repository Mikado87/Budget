package ru.amaev.budget.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class DataNotFound : RuntimeException()

@ResponseStatus(HttpStatus.CONFLICT)
class AlreadyExist : RuntimeException()

@ResponseStatus(HttpStatus.BAD_REQUEST)
class IncorrectRequest : RuntimeException()
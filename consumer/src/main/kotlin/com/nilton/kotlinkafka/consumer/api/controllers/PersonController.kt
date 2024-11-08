package com.nilton.kotlinkafka.consumer.api.controllers

import com.nilton.kotlinkafka.consumer.models.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PersonController {

    @GetMapping("/person")
    fun get(): Person {
        return Person("personId","teste", "teste2")
    }
}
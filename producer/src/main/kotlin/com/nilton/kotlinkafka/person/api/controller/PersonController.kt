package com.nilton.kotlinkafka.person.api.controller

import com.nilton.kotlinkafka.person.models.Person
import com.nilton.kotlinkafka.person.models.dto.CreatePersonDto
import com.nilton.kotlinkafka.person.models.dto.PersonDto
import com.nilton.kotlinkafka.person.models.dto.UpdatePersonDto
import com.nilton.kotlinkafka.person.producers.PersonProducer
import com.nilton.kotlinkafka.person.services.PersonService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/persons")
class PersonController(
    @Autowired
    val personProducer: PersonProducer,
    @Autowired
    val personService: PersonService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("")
    fun post(@RequestBody personDto : CreatePersonDto): ResponseEntity<Any>
    {
        return try {
            val person = Person(
                name = personDto.name,
                document = personDto.document,
            )

            personProducer.sendCreatePersonMessage(person)
            ResponseEntity.ok().build()
        }
        catch (e: Exception) {
            log.error("Error creating the person: {}", e.message)
            ResponseEntity.internalServerError().build()
        }
    }

    @PutMapping("")
    fun put(@RequestBody personDto : UpdatePersonDto): ResponseEntity<Any>
    {
        return try {
            val person = Person(
                personId = personDto.personId,
                name = personDto.name,
                document = personDto.document
            )

            personProducer.sendUpdatePersonMessage(person)
            ResponseEntity.ok().build()
        }
        catch (e: Exception) {
            log.error("Error updating the person: {}", e.message)
            ResponseEntity.internalServerError().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : String): ResponseEntity<Any>
    {
        return try {
            val person = Person(id = id)
            personProducer.sendDeletePersonMessage(person)
            ResponseEntity.ok().build()
        }
        catch (e: Exception) {
            log.error("Error deleting the person: {}", e.message)
            ResponseEntity.internalServerError().build()
        }
    }

    @GetMapping("/{personId}")
    fun getById(@PathVariable personId : String) : ResponseEntity<Any>{
        val person = personService
            .findByPersonId(personId)

        return if (person != null) {
            val dto = PersonDto(person.name, person.document, person.personId)
            ResponseEntity.ok().body(dto)
        }
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/listAll")
    fun listAll(): ResponseEntity<Any>{
        val personList = personService
            .listPersons()

        if (personList.isNotEmpty()) {
            val personDtoList = personList.map { PersonDto(it.name, it.document) }
            return ResponseEntity.ok().body(personDtoList)
        }

        return ResponseEntity.notFound().build()
    }
}
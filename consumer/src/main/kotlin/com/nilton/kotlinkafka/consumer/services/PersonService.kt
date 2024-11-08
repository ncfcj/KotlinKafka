package com.nilton.kotlinkafka.consumer.services

import com.nilton.kotlinkafka.consumer.data.repositories.IPersonRepository
import com.nilton.kotlinkafka.consumer.models.CreatePersonDto
import com.nilton.kotlinkafka.consumer.models.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PersonService(
    @Autowired
    val personRepository: IPersonRepository
)
{
    fun create(personDto : CreatePersonDto) : Person
    {
        val person = Person(
            UUID.randomUUID().toString(),
            personDto.name,
            personDto.document,
            UUID.randomUUID().toString())
        personRepository.insert(person);

        return person;
    }

    fun update(person : Person)
    {
        val savedPerson = personRepository
            .findPersonById(person.personId.toString())
            .orElseThrow { throw RuntimeException("Person not found!") }

        savedPerson.name = person.name
        savedPerson.document = person.document
        savedPerson.personId = person.personId

        personRepository.save(savedPerson)
    }

    fun delete(personId : String)
    {
        val savedPerson = personRepository
            .findPersonById(personId)
            .orElseThrow { throw RuntimeException("Person not found!") }

        personRepository.delete(savedPerson)
    }
}
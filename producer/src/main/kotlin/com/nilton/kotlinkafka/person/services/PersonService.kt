package com.nilton.kotlinkafka.person.services

import com.nilton.kotlinkafka.person.data.IPersonRepository
import com.nilton.kotlinkafka.person.models.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PersonService(
    @Autowired
    val personRepository: IPersonRepository
)
{
    fun findByPersonId(personId : String) : Person?
    {
        val person = personRepository
            .findByPersonId(personId)
            .getOrNull()

        return person
    }

    fun listPersons() : List<Person>{
        return personRepository.findAll()
    }
}
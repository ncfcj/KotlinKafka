package com.nilton.kotlinkafka.person.data

import com.nilton.kotlinkafka.person.models.Person
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface IPersonRepository : MongoRepository<Person, ObjectId> {
    @Query("{'personId':?0}")
    fun findByPersonId(personId: String) : Optional<Person>
}
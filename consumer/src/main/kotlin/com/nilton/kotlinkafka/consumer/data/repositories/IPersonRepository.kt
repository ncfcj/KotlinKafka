package com.nilton.kotlinkafka.consumer.data.repositories

import com.nilton.kotlinkafka.consumer.models.Person
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface IPersonRepository : MongoRepository<Person, ObjectId> {
    @Query("{'personId':?0}")
    fun findPersonById(personId: String) : Optional<Person>
}
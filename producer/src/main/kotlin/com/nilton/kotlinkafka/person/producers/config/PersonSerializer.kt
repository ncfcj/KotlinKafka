package com.nilton.kotlinkafka.person.producers.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.nilton.kotlinkafka.person.models.Person
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class PersonSerializer : Serializer<Person> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: Person?) : ByteArray? {
        log.info("Serializing Person...")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Person to ByteArray[]")
        )
    }

    override fun close() {}
}
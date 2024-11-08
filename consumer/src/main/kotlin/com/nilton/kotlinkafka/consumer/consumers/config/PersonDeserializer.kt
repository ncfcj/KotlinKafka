package com.nilton.kotlinkafka.consumer.consumers.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.nilton.kotlinkafka.consumer.models.Person
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory

class PersonDeserializer : Deserializer<Person> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): Person {
        log.info("Deserializing Person from topic: {}", topic)
        return objectMapper.readValue(data, Person::class.java)
    }
}
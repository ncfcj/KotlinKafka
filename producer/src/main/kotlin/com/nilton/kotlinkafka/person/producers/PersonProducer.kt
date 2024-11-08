package com.nilton.kotlinkafka.person.producers

import com.nilton.kotlinkafka.person.models.Person
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonProducer(
    @Value("\${spring.kafka.topics.create-person}")
    private val createPersonTopic: String,
    @Value("\${spring.kafka.topics.update-person}")
    private val updatePersonTopic: String,
    @Value("\${spring.kafka.topics.delete-person}")
    private val deletePersonTopic: String,
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Person>
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val key : UUID = UUID.randomUUID()

    fun sendCreatePersonMessage(person: Person) {
        log.info("Sending create person message to Kafka - {}", person)

        val message : Message<Person> = MessageBuilder
            .withPayload(person)
            .setHeader(KafkaHeaders.TOPIC, createPersonTopic)
            .setHeader(KafkaHeaders.KEY, key)
            .build()

        kafkaTemplate.send(message)
        log.info("Create person message sent with success")
    }

    fun sendUpdatePersonMessage(person: Person) {
        log.info("Sending update person message to Kafka - {}", person)

        val message : Message<Person> = MessageBuilder
            .withPayload(person)
            .setHeader(KafkaHeaders.TOPIC, updatePersonTopic)
            .setHeader(KafkaHeaders.KEY, key)
            .build()

        kafkaTemplate.send(message)
        log.info("Update person message sent with success")
    }

    fun sendDeletePersonMessage(person: Person) {
        log.info("Sending delete person message to Kafka - {}", person)

        val message : Message<Person> = MessageBuilder
            .withPayload(person)
            .setHeader(KafkaHeaders.TOPIC, deletePersonTopic)
            .setHeader(KafkaHeaders.KEY, key)
            .build()

        kafkaTemplate.send(message)
        log.info("Delete person message sent with success")
    }
}

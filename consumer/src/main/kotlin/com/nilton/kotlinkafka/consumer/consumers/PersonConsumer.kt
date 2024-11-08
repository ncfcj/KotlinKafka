package com.nilton.kotlinkafka.consumer.consumers

import com.nilton.kotlinkafka.consumer.models.CreatePersonDto
import com.nilton.kotlinkafka.consumer.models.Person
import com.nilton.kotlinkafka.consumer.services.PersonService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PersonConsumer(
    @Autowired
    private val personService: PersonService
) {
    private val logger = LoggerFactory.getLogger(PersonConsumer::class.java)

    @KafkaListener(topics = ["\${spring.kafka.topics.create-person}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listenCreatePerson(consumerRecord: ConsumerRecord<String, Person>) {
        logger.info("Create person message received {}", consumerRecord)

        val person : Person = consumerRecord.value()
        val createPersonDto = CreatePersonDto(
            name = person.name,
            document = person.document
        )
        val personCreated = personService.create(createPersonDto);

        logger.info("Person created on the db {}", personCreated)
    }

    @KafkaListener(topics = ["\${spring.kafka.topics.update-person}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listenUpdatePerson(consumerRecord: ConsumerRecord<String, Person>) {
        logger.info("Update person message {}", consumerRecord)

        val person : Person = consumerRecord.value()
        personService.update(person)

        logger.info("Person updated on the db {}", person)
    }

    @KafkaListener(topics = ["\${spring.kafka.topics.delete-person}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listenDeletePerson(consumerRecord: ConsumerRecord<String, Person>) {
        logger.info("Delete person message received {}", consumerRecord)

        val person : Person = consumerRecord.value()
        val personId = person.personId
        personService.delete(personId.toString())

        logger.info("Person with id {} has been deleted from the db", personId)
    }
}
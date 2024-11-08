package com.nilton.kotlinkafka.config

import com.nilton.kotlinkafka.person.models.Person
import com.nilton.kotlinkafka.person.producers.config.PersonSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.UUIDSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
)
{
    @Bean
    fun producerFactory(): ProducerFactory<String, Person> {
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to UUIDSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to PersonSerializer::class.java
        )
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplateForPerson(): KafkaTemplate<String, Person> {
        return KafkaTemplate(producerFactory())
    }
}
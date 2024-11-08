package com.nilton.kotlinkafka.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String?,
    @Value("\${spring.kafka.topics.create-person}")
    private val createPersonTopic: String,
    @Value("\${spring.kafka.topics.update-person}")
    private val updatePersonTopic: String,
    @Value("\${spring.kafka.topics.delete-person}")
    private val deletePersonTopic: String,
)
{
    @Bean
    fun kafkaAdmin() : KafkaAdmin {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        return KafkaAdmin(configs)
    }

    @Bean
    fun createPersonTopic() : NewTopic {
        return NewTopic(createPersonTopic, 1 ,1.toShort())
    }

    @Bean
    fun updatePersonTopic() : NewTopic {
        return NewTopic(updatePersonTopic, 1 ,1.toShort())
    }

    @Bean
    fun deletePersonTopic() : NewTopic {
        return NewTopic(deletePersonTopic, 1 ,1.toShort())
    }
}
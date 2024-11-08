package com.nilton.kotlinkafka.consumer.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id

import org.springframework.data.mongodb.core.mapping.Document

@Document("persons")
data class Person @JsonCreator constructor(
    @Id
    @JsonProperty("id")
    var id: String? = null,

    @JsonProperty("name")
    var name: String? = null,

    @JsonProperty("document")
    var document: String? = null,

    @JsonProperty("personId")
    var personId: String? = null,
)

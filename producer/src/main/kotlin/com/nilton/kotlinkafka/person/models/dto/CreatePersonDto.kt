package com.nilton.kotlinkafka.person.models.dto

import org.bson.codecs.pojo.annotations.BsonProperty

data class CreatePersonDto (
        @BsonProperty("name")
        var name: String? = null,

        @BsonProperty("document")
        var document: String? = null,
)
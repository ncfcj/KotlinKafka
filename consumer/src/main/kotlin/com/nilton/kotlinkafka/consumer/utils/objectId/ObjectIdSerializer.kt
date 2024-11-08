package com.nilton.kotlinkafka.consumer.utils.objectId

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.bson.types.ObjectId

class ObjectIdSerializer : StdSerializer<ObjectId>(ObjectId::class.java) {
    override fun serialize(value: ObjectId, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toHexString())
    }
}
package com.github.rescuesaiyou.codingtesthonma.web.api.json.user

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import org.springframework.boot.jackson.JsonComponent
import java.util.UUID

@JsonComponent
class UserIdJson {

    class Serializer : JsonSerializer<UserId>() {
        override fun serialize(UserId: UserId, gen: JsonGenerator, serializers: SerializerProvider) =
            gen.writeObject(UserId.value)
    }

    class Deserializer : JsonDeserializer<UserId>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): UserId =
            UserId(p.readValueAs(UUID::class.java))
    }
}

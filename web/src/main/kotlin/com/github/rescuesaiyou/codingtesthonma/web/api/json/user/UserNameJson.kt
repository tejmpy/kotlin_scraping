package com.github.rescuesaiyou.codingtesthonma.web.api.json.user

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserName
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class UserNameJson {

    class Serializer : JsonSerializer<UserName>() {
        override fun serialize(
            UserName: UserName,
            gen: JsonGenerator,
            serializers: SerializerProvider
        ) = gen.writeString(UserName.value)
    }

    class Deserializer : JsonDeserializer<UserName>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): UserName =
            UserName(p.valueAsString)
    }
}

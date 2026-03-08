package me.artofluxis.mod.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import me.artofluxis.game.trait.Trait

object TraitSerializer : KSerializer<Trait> {
    override val descriptor: SerialDescriptor
        get() = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): Trait {
        val jsonDecoder = decoder as? JsonDecoder
            ?: error("TraitSerializer only works with JSON")

        val element = jsonDecoder.decodeJsonElement()
        val jsonObject = element.jsonObject

        val traitName = jsonObject["id"]!!.jsonPrimitive.content

        return Trait.from(traitName, jsonObject)
    }

    override fun serialize(encoder: Encoder, value: Trait) {
        error("This is not supposed to be serialized")
    }
}
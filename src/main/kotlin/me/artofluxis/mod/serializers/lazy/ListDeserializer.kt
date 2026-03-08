package me.artofluxis.mod.serializers.lazy

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import me.artofluxis.game.mod.LazyDeserializer

class ListDeserializer<T>(
    private val elementSerializer: KSerializer<T>
) : LazyDeserializer<List<T>> {
    override val descriptor: SerialDescriptor = ListSerializer(elementSerializer).descriptor

    override fun deserialize(decoder: Decoder): () -> List<T> {
        val jsonDecoder = decoder as? JsonDecoder ?: error("Requires JsonDecoder")
        val element = jsonDecoder.decodeJsonElement()
        return { Json.decodeFromJsonElement(ListSerializer(elementSerializer), element) }
    }
}

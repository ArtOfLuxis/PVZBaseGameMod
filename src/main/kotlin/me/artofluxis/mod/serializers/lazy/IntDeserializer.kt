package me.artofluxis.mod.serializers.lazy

import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import me.artofluxis.game.mod.LazyDeserializer

object IntDeserializer : LazyDeserializer<Int> {
    override val descriptor =
        PrimitiveSerialDescriptor("IntNumber", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): () -> Int {
        val number = decoder.decodeInt()
        return { number }
    }
}

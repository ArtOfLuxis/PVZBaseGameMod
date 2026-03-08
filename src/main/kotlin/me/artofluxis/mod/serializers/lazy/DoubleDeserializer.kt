package me.artofluxis.mod.serializers.lazy

import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import me.artofluxis.game.mod.LazyDeserializer

object DoubleDeserializer : LazyDeserializer<Double> {
    override val descriptor =
        PrimitiveSerialDescriptor("DoubleNumber", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): () -> Double {
        val number = decoder.decodeDouble()
        return { number }
    }
}

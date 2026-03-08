package me.artofluxis.mod.serializers.lazy

import me.artofluxis.game.effects.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import me.artofluxis.game.mod.LazyDeserializer
import me.artofluxis.game.registries.*

object EffectDeserializer : LazyDeserializer<Effect> {
    override val descriptor =
        PrimitiveSerialDescriptor("EffectType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): () -> Effect {
        val id = decoder.decodeString()
        return { EffectRegistry.get(id) }
    }
}

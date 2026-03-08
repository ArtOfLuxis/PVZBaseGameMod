package me.artofluxis.mod.serializers.lazy

import me.artofluxis.game.game.hitbox.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import me.artofluxis.game.mod.LazyDeserializer
import me.artofluxis.game.registries.*

object HitboxDeserializer : LazyDeserializer<Hitbox> {
    override val descriptor =
        PrimitiveSerialDescriptor("Hitbox", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): () -> Hitbox {
        val id = decoder.decodeString()
        return { HitboxRegistry.get(id) }
    }
}

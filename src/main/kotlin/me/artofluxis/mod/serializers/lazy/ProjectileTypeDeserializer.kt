package me.artofluxis.mod.serializers.lazy

import me.artofluxis.game.game.types.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import me.artofluxis.game.mod.LazyDeserializer
import me.artofluxis.game.registries.*

object ProjectileTypeDeserializer : LazyDeserializer<ProjectileType> {
    override val descriptor =
        PrimitiveSerialDescriptor("ProjectileType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): () -> ProjectileType {
        val id = decoder.decodeString()
        return { ProjectileRegistry.get(id) }
    }
}

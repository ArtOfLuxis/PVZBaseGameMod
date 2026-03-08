package me.artofluxis.mod.serializers.lazy

import me.artofluxis.game.Position
import kotlinx.serialization.builtins.*
import kotlinx.serialization.encoding.*
import me.artofluxis.game.mod.LazyDeserializer

object PositionDeserializer : LazyDeserializer<Position> {
    override val descriptor =
        ListSerializer(Double.serializer()).descriptor

    override fun deserialize(decoder: Decoder): () -> Position {
        val list = decoder.decodeSerializableValue(ListSerializer(Double.serializer()))
        require(list.size == 2)
        return { Position(list[0], list[1]) }
    }
}

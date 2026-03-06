package me.artofluxis.traits.tiles

import effects.Effect
import game.objects.LawnObject
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.*
import me.artofluxis.serializers.EffectSerializer
import trait.*

class EffectApplierTileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "effect" to EffectSerializer,
    "effectTime" to Double.serializer(),
), TraitType.TILE) {
    override val values = deserialize(jsonObject)

    val effect get() = get<Effect>("effect")
    val effectTime get() = get<Double>("effectTime")
}

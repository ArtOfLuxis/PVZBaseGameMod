package me.artofluxis.mod.traits.tiles

import effects.Effect
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.*
import trait.*

class EffectApplierTileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "effect" to me.artofluxis.mod.serializers.EffectSerializer,
    "effectTime" to Double.serializer(),
), TraitType.TILE) {
    override val values = deserialize(jsonObject)

    val effect get() = get<Effect>("effect")
    val effectTime get() = get<Double>("effectTime")
}

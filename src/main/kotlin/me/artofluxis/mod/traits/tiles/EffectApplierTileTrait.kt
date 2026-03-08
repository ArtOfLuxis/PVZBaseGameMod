package me.artofluxis.mod.traits.tiles

import me.artofluxis.game.effects.Effect
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer
import me.artofluxis.mod.serializers.lazy.EffectDeserializer

class EffectApplierTileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "effect" to EffectDeserializer,
    "effectTime" to DoubleDeserializer,
), TraitType.TILE, jsonObject) {

    val effect get() = get<Effect>("effect")
    val effectTime get() = get<Double>("effectTime")
}

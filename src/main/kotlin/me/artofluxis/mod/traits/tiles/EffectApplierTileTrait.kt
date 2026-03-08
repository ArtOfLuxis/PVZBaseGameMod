package me.artofluxis.mod.traits.tiles

import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.effects.Effect
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.trait.Trait
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.TraitType
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

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnTile) {
            "Parent for ${this::class.simpleName} must be a ${LawnTile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return EffectApplierTileInstance(parent, this)
    }
}

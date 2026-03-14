package me.artofluxis.mod.traits.tiles.generic

import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.effects.Effect
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.mod.trait.Trait
import me.artofluxis.game.mod.trait.TraitInstance
import me.artofluxis.game.mod.trait.TraitType
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

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnTile) {
            "Parent for ${this::class.simpleName} must be a ${LawnTile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return EffectApplierTileInstance(parent, this)
    }
}

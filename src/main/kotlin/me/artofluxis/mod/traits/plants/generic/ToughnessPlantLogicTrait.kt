package me.artofluxis.mod.traits.plants.generic

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.mod.trait.*
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer

class ToughnessPlantLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "toughness" to DoubleDeserializer,
), TraitType.PLANT, jsonObject) {

    val toughness get() = get<Double>("toughness")

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnPlant) {
            "Parent for ${this::class.simpleName} must be a ${LawnPlant::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return ToughnessPlantLogicInstance(parent, this)
    }
}

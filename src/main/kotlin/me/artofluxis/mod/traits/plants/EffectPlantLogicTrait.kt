package me.artofluxis.mod.traits.plants

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*

class EffectPlantLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(), TraitType.PLANT, jsonObject) {

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnPlant) {
            "Parent for ${this::class.simpleName} must be a ${LawnPlant::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return EffectPlantLogicInstance(parent, this)
    }
}

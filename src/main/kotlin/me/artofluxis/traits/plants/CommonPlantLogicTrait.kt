package me.artofluxis.traits.plants

import game.objects.*
import game.objects.logic.*
import kotlinx.serialization.json.*
import trait.*

class CommonPlantLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(), TraitType.PLANT) {
    override val values = deserialize(jsonObject)

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnPlant) {
            "Parent for ${this::class.simpleName} must be a ${LawnPlant::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return CommonPlantLogicInstance(parent, this)
    }
}

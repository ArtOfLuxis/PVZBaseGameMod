package me.artofluxis.mod.traits.generic

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*
import me.artofluxis.mod.traits.plants.generic.EffectPlantLogicInstance

class GenericAnimationTickerTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(), TraitType.GENERIC, jsonObject) {

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is TickableLawnObject) {
            "Parent for ${this::class.simpleName} must be a ${TickableLawnObject::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return GenericAnimationTickerInstance(parent, this)
    }
}

package me.artofluxis.mod.traits.generic

import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.TickableLawnObject
import me.artofluxis.game.mod.trait.Trait
import me.artofluxis.game.mod.trait.TraitInstance
import me.artofluxis.game.mod.trait.TraitType

class GenericAnimationTickerTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(), TraitType.GENERIC, jsonObject) {

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is TickableLawnObject) {
            "Parent for ${this::class.simpleName} must be a ${TickableLawnObject::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return GenericAnimationTickerInstance(parent, this)
    }
}

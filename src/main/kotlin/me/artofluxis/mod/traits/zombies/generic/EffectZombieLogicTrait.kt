package me.artofluxis.mod.traits.zombies.generic

import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.trait.Trait
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.TraitType

class EffectZombieLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(), TraitType.ZOMBIE, jsonObject) {

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnZombie) {
            "Parent for ${this::class.simpleName} must be a ${LawnZombie::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return EffectZombieLogicInstance(parent, this)
    }
}

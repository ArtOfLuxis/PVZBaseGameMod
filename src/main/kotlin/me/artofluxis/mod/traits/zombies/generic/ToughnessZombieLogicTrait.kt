package me.artofluxis.mod.traits.zombies.generic

import kotlinx.serialization.json.*
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.trait.Trait
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.TraitType
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer

class ToughnessZombieLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "toughness" to DoubleDeserializer,
), TraitType.ZOMBIE, jsonObject) {

    val toughness get() = get<Double>("toughness")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnZombie) {
            "Parent for ${this::class.simpleName} must be a ${LawnZombie::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return ToughnessZombieLogicInstance(parent, this)
    }
}

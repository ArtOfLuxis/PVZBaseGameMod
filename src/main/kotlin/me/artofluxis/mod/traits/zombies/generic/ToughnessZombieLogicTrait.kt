package me.artofluxis.mod.traits.zombies.generic

import kotlinx.serialization.json.*
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.mod.trait.Trait
import me.artofluxis.game.mod.trait.TraitInstance
import me.artofluxis.game.mod.trait.TraitType
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer

class ToughnessZombieLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "toughness" to DoubleDeserializer,
), TraitType.ZOMBIE, jsonObject) {

    val toughness get() = get<Double>("toughness")

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnZombie) {
            "Parent for ${this::class.simpleName} must be a ${LawnZombie::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return ToughnessZombieLogicInstance(parent, this)
    }
}

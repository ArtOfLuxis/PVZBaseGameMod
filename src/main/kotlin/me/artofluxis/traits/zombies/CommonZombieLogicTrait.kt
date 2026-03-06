package me.artofluxis.traits.zombies

import game.objects.*
import game.objects.logic.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.*
import trait.*

class CommonZombieLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "eatDPS" to Double.serializer(),
    "speed" to Double.serializer(),
    "additionalSpeed" to Double.serializer()
), TraitType.ZOMBIE) {
    override val values = deserialize(jsonObject)

    val eatDPS get() = get<Double>("eatDPS")
    val speed get() = get<Double>("speed")
    val additionalSpeed get() = get<Double>("additionalSpeed")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnZombie) {
            "Parent for ${this::class.simpleName} must be a ${LawnZombie::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return CommonZombieLogicInstance(parent, this)
    }
}

package me.artofluxis.mod.traits.zombies

import kotlinx.serialization.json.*
import me.artofluxis.game.game.hitbox.Hitbox
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.trait.Trait
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.TraitType
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer
import me.artofluxis.mod.serializers.lazy.HitboxDeserializer

class MovementZombieLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "eatDPS" to DoubleDeserializer,
    "speed" to DoubleDeserializer,
    "additionalSpeed" to DoubleDeserializer,
    "eatingHitbox" to HitboxDeserializer
), TraitType.ZOMBIE, jsonObject) {

    val eatDPS get() = get<Double>("eatDPS")
    val speed get() = get<Double>("speed")
    val additionalSpeed get() = get<Double>("additionalSpeed")
    val eatingHitbox get() = get<Hitbox>("eatingHitbox")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnZombie) {
            "Parent for ${this::class.simpleName} must be a ${LawnZombie::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return MovementZombieLogicInstance(parent, this)
    }
}

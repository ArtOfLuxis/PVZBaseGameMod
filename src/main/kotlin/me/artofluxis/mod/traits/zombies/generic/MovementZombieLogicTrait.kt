package me.artofluxis.mod.traits.zombies.generic

import kotlinx.serialization.json.*
import me.artofluxis.game.game.hitbox.Hitbox
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.mod.trait.Trait
import me.artofluxis.game.mod.trait.TraitInstance
import me.artofluxis.game.mod.trait.TraitType
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

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnZombie) {
            "Parent for ${this::class.simpleName} must be a ${LawnZombie::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return MovementZombieLogicInstance(parent, this)
    }
}

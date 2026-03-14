package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.mod.trait.*
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer

class StraightProjectileLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
        "speed" to DoubleDeserializer
), TraitType.PROJECTILE, jsonObject) {

    val speed get() = get<Double>("speed")

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return StraightProjectileLogicInstance(parent, this)
    }
}

package me.artofluxis.traits.projectiles

import game.objects.*
import game.objects.logic.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.*
import trait.*

class StraightProjectileLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
        "speed" to Double.serializer()
), TraitType.PROJECTILE) {
    override val values = deserialize(jsonObject)

    val speed = get<Double>("speed")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return StraightProjectileLogicInstance(parent, this)
    }
}

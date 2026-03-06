package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*

class StraightProjectileLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
        "speed" to Double.serializer()
), TraitType.PROJECTILE) {
    override val values = deserialize(jsonObject)

    val speed get() = get<Double>("speed")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return StraightProjectileLogicInstance(parent, this)
    }
}

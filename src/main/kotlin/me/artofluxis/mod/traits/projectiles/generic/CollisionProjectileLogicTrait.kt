package me.artofluxis.mod.traits.projectiles.generic

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.game.hitbox.Hitbox
import me.artofluxis.game.trait.*
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer
import me.artofluxis.mod.serializers.lazy.HitboxDeserializer

class CollisionProjectileLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "damage" to DoubleDeserializer,
), TraitType.PROJECTILE, jsonObject) {

    val damage get() = get<Double>("damage")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return CollisionProjectileLogicInstance(parent, this)
    }
}

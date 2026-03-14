package me.artofluxis.mod.traits.projectiles.generic

import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.game.mod.trait.Trait
import me.artofluxis.game.mod.trait.TraitInstance
import me.artofluxis.game.mod.trait.TraitType
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer

class CollisionProjectileLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "damage" to DoubleDeserializer,
), TraitType.PROJECTILE, jsonObject) {

    val damage get() = get<Double>("damage")

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return CollisionProjectileLogicInstance(parent, this)
    }
}

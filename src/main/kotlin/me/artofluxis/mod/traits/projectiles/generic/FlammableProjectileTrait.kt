package me.artofluxis.mod.traits.projectiles.generic

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.game.types.ProjectileType
import me.artofluxis.game.mod.trait.*
import me.artofluxis.mod.serializers.lazy.ProjectileTypeDeserializer

class FlammableProjectileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "newProjectile" to ProjectileTypeDeserializer
), TraitType.PROJECTILE, jsonObject) {

    val newProjectile get() = get<ProjectileType>("newProjectile")

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return FlammableProjectileInstance(parent, this)
    }
}

package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*

class FlammableProjectileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
        //"effects" to String.serializer() // make a separate class soon
), TraitType.PROJECTILE) {


    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return FlammableProjectileInstance(parent, this)
    }
}

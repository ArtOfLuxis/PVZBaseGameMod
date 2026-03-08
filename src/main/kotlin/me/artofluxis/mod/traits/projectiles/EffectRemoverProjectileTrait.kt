package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.effects.*
import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer
import me.artofluxis.mod.serializers.lazy.EffectDeserializer

class EffectRemoverProjectileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "effect" to EffectDeserializer,
), TraitType.PROJECTILE, jsonObject) {

    val effect get() = get<Effect>("effect")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return EffectRemoverProjectileInstance(parent, this)
    }
}

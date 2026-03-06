package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.effects.*
import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.*
import me.artofluxis.game.trait.*

class EffectApplierProjectileTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "effect" to me.artofluxis.mod.serializers.EffectSerializer,
    "effectTime" to Double.serializer(),
), TraitType.PROJECTILE) {
    override val values = deserialize(jsonObject)

    val effect get() = get<Effect>("effect")
    val effectTime get() = get<Double>("effectTime")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnProjectile) {
            "Parent for ${this::class.simpleName} must be a ${LawnProjectile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return EffectApplierProjectileInstance(parent, this)
    }
}

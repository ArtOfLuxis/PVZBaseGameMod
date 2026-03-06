package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.trait.*
import me.artofluxis.game.trait.events.alive.*

class EffectApplierProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: EffectApplierProjectileTrait
) : TraitInstance(parent, trait), ProjectileHitObjectTraitListener {
    override fun projectileHitObject(obj: LawnObject) {
        if (obj is AliveLawnObject) obj.applyEffect(trait.effect, trait.effectTime)
    }
}

package me.artofluxis.traits.projectiles

import game.objects.*
import game.objects.logic.*
import trait.*
import trait.events.alive.*

class EffectApplierProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: EffectApplierProjectileTrait
) : TraitInstance(parent, trait), ProjectileHitObjectTraitListener {
    override fun projectileHitObject(obj: LawnObject) {
        if (obj is AliveLawnObject) obj.applyEffect(trait.effect, trait.effectTime)
    }
}

package me.artofluxis.mod.traits.projectiles.generic

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.mod.trait.*
import me.artofluxis.mod.extensions.AliveLawnObjectExtensions.removeEffect
import me.artofluxis.mod.listeners.alive.ProjectileHitObjectTraitListener

class EffectRemoverProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: EffectRemoverProjectileTrait
) : ObjectTraitInstance,
    ProjectileHitObjectTraitListener
{
    override fun projectileHitObject(obj: LawnObject, damage: Double) {
        if (obj is AliveLawnObject) obj.removeEffect(trait.effect)
    }
}

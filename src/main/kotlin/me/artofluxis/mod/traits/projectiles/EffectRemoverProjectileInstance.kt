package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.trait.*
import me.artofluxis.game.trait.events.alive.*

class EffectRemoverProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: EffectRemoverProjectileTrait
) : TraitInstance(parent, trait),
    ProjectileHitObjectTraitListener
{
    override fun projectileHitObject(obj: LawnObject, damage: Double) {
        if (obj is AliveLawnObject) obj.removeEffect(trait.effect)
    }
}

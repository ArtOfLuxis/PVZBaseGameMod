package me.artofluxis.mod.listeners.alive

import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.game.mod.trait.TraitTrigger

interface HitByProjectileTraitListener: TraitTrigger {
    fun hitByProjectile(projectile: LawnProjectile, damage: Double)
}

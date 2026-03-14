package me.artofluxis.mod.extensions

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.mod.listeners.alive.ProjectileHitObjectTraitListener

object LawnProjectileExtensions {
    fun LawnProjectile.projectileHitObject(obj: LawnObject, damage: Double) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is ProjectileHitObjectTraitListener) trait.projectileHitObject(obj, damage)
        }
    }
}
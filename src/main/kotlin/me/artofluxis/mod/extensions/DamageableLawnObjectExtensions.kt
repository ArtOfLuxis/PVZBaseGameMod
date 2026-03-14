package me.artofluxis.mod.extensions

import me.artofluxis.game.game.objects.DamageableLawnObject
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.mod.trait.events.alive.*
import me.artofluxis.mod.listeners.alive.DamagedTraitListener
import me.artofluxis.mod.listeners.alive.DeathTraitListener
import me.artofluxis.mod.listeners.alive.HitByProjectileTraitListener

object DamageableLawnObjectExtensions {
    fun DamageableLawnObject.hitByProjectile(projectile: LawnProjectile, damage: Double) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is HitByProjectileTraitListener) trait.hitByProjectile(projectile, damage)
            if (trait is DamagedTraitListener) trait.damagedBy(projectile, damage)
        }
    }

    fun DamageableLawnObject.damagedByZombie(zombie: LawnZombie, damage: Double) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is DamagedByZombieTraitListener) { trait.damagedByZombie(zombie, damage) }
            if (trait is DamagedTraitListener) { trait.damagedBy(zombie, damage) }
        }
    }

    fun DamageableLawnObject.death(killer: LawnObject) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is DeathTraitListener) { trait.onObjectDeath(killer) }
        }
    }
}
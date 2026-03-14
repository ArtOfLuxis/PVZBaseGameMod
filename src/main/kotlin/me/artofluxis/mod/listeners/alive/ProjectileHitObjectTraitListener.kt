package me.artofluxis.mod.listeners.alive

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.mod.trait.TraitTrigger

interface ProjectileHitObjectTraitListener: TraitTrigger {
    fun projectileHitObject(obj: LawnObject, damage: Double)
}

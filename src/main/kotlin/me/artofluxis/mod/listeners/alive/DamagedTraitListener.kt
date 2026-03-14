package me.artofluxis.mod.listeners.alive

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.mod.trait.TraitTrigger

interface DamagedTraitListener: TraitTrigger {
    fun damagedBy(damager: LawnObject, damage: Double)
}

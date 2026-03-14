package me.artofluxis.mod.listeners.alive

import me.artofluxis.game.game.objects.*
import me.artofluxis.game.mod.trait.TraitTrigger

interface DeathTraitListener: TraitTrigger {
    fun onObjectDeath(killer: LawnObject)
}

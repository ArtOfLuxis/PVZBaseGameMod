package me.artofluxis.mod.listeners.generic

import me.artofluxis.game.mod.trait.TraitTrigger

interface TickTraitListener: TraitTrigger {
    fun tick(deltaTime: Double)
}

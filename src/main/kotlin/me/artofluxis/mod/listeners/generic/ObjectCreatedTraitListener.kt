package me.artofluxis.mod.listeners.generic

import me.artofluxis.game.mod.trait.TraitTrigger

interface ObjectCreatedTraitListener: TraitTrigger {
    fun onCreation()
}

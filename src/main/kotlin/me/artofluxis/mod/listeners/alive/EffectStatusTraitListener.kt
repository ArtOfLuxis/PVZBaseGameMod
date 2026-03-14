package me.artofluxis.mod.listeners.alive

import me.artofluxis.game.effects.Effect
import me.artofluxis.game.mod.trait.TraitTrigger

interface EffectStatusTraitListener: TraitTrigger {
    fun appliedEffect(effect: Effect, effectTime: Double)
    fun removedEffect(effect: Effect)
}

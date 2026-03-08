package me.artofluxis.mod.traits.plants.generic

import me.artofluxis.game.effects.Effect
import me.artofluxis.game.game.objects.logic.LawnPlant
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.alive.EffectStatusTraitListener
import me.artofluxis.game.trait.events.general.TickTraitListener
import me.artofluxis.mod.Util

class EffectPlantLogicInstance(
    override val parent: LawnPlant,
    override val trait: EffectPlantLogicTrait
) : TraitInstance(parent, trait),
    EffectStatusTraitListener,
    TickTraitListener
{
    override fun tick(deltaTime: Double) {
        val effects = parent.effects.toList()
        for ((effect, effectTimer) in effects) {
            if (effectTimer.isExpired()) {
                removedEffect(effect)
            }
        }
    }

    override fun appliedEffect(effect: Effect, effectTime: Double) {
        Util.applyEffect(parent, effect, effectTime)
    }

    override fun removedEffect(effect: Effect) {
        Util.removeEffect(parent, effect)
    }
}

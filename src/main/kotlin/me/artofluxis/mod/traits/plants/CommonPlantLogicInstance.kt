package me.artofluxis.mod.traits.plants

import effects.Effect
import game.objects.logic.LawnPlant
import me.artofluxis.mod.Util
import trait.TraitInstance
import trait.events.alive.EffectStatusTraitListener
import trait.events.alive.TickTraitListener

class CommonPlantLogicInstance(
    override val parent: LawnPlant,
    override val trait: CommonPlantLogicTrait
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

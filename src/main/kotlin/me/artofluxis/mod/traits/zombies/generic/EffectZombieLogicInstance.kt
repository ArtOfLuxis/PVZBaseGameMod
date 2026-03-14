package me.artofluxis.mod.traits.zombies.generic

import me.artofluxis.game.effects.Effect
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.mod.trait.ObjectTraitInstance
import me.artofluxis.mod.listeners.alive.EffectStatusTraitListener
import me.artofluxis.mod.listeners.generic.TickTraitListener
import me.artofluxis.mod.Util

class EffectZombieLogicInstance(
    override val parent: LawnZombie,
    override val trait: EffectZombieLogicTrait
) : ObjectTraitInstance,
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

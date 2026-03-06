package me.artofluxis.traits.plants

import effects.*
import effects.visual.*
import game.objects.logic.*
import trait.*
import trait.events.alive.*

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
        val currentTime = parent.effects[effect]?.remaining() ?: 0.0
        if (effectTime > currentTime)
            parent.effects[effect] = Timer.start(effectTime)

        effect.visuals.forEach {
            when (it) {
                is EffectVisualTint -> {
                    parent.highlightFilter.colors.add(it.color)
                }
            }
        }
    }

    override fun removedEffect(effect: Effect) {
        parent.effects.remove(effect)
        effect.visuals.forEach {
            when (it) {
                is EffectVisualTint -> {
                    parent.highlightFilter.colors.remove(it.color)
                }
            }
        }
    }
}

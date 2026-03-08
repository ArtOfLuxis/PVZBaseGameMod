package me.artofluxis.mod

import me.artofluxis.game.Timer
import me.artofluxis.game.effects.Effect
import me.artofluxis.game.effects.visual.EffectVisualTint
import me.artofluxis.game.game.objects.AliveLawnObject

object Util {

    fun applyEffect(obj: AliveLawnObject, effect: Effect, effectTime: Double) {
        if (effect in obj.effects) {
            val currentTime = obj.effects[effect]!!.remaining()
            if (effectTime > currentTime)
                obj.effects[effect] = Timer.start(effectTime)

        } else {
            obj.effects[effect] = Timer.start(effectTime)
            effect.visuals.forEach {
                when (it) {
                    is EffectVisualTint -> {
                        obj.highlightFilter.colors.add(it.color)
                    }
                }
            }
        }
    }

    fun removeEffect(obj: AliveLawnObject, effect: Effect) {
        obj.effects.remove(effect)
        effect.visuals.forEach {
            when (it) {
                is EffectVisualTint -> {
                    obj.highlightFilter.colors.remove(it.color)
                }
            }
        }
    }
}
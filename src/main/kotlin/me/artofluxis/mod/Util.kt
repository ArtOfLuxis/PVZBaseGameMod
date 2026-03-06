package me.artofluxis.mod

import effects.Effect
import effects.visual.EffectVisualTint
import game.objects.AliveLawnObject

object Util {

    fun applyEffect(obj: AliveLawnObject, effect: Effect, effectTime: Double) {
        val currentTime = obj.effects[effect]?.remaining() ?: 0.0
        if (effectTime > currentTime)
            obj.effects[effect] = Timer.start(effectTime)

        effect.visuals.forEach {
            when (it) {
                is EffectVisualTint -> {
                    obj.highlightFilter.colors.add(it.color)
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
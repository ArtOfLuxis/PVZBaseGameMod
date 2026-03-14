package me.artofluxis.mod.extensions

import me.artofluxis.game.effects.Effect
import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.game.objects.AliveLawnObject
import me.artofluxis.mod.listeners.alive.EffectStatusTraitListener

object AliveLawnObjectExtensions {
    fun AliveLawnObject.applyEffect(effect: Effect, effectTime: Double) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is EffectStatusTraitListener) trait.appliedEffect(effect, effectTime)
        }
    }

    fun AliveLawnObject.removeEffect(effect: Effect) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is EffectStatusTraitListener) trait.removedEffect(effect)
        }
    }

    fun AliveLawnObject.getStat(
        baseValue: Double,
        statType: EffectModifierType
    ): Double {
        var value = baseValue

        val modifiers = effects
            .flatMap { it.key.effects }
            .filter { it.type == statType }
            .sortedBy { it.operationOrder.order }

        modifiers.forEach {
            value = it.operationType.operation(value, it.value)
        }

        return value
    }
}
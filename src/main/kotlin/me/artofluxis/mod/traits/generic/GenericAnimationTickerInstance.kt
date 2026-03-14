package me.artofluxis.mod.traits.generic

import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.game.objects.AliveLawnObject
import me.artofluxis.game.game.objects.TickableLawnObject
import me.artofluxis.game.mod.trait.ObjectTraitInstance
import me.artofluxis.mod.listeners.generic.TickTraitListener
import me.artofluxis.mod.extensions.AliveLawnObjectExtensions.getStat

class GenericAnimationTickerInstance(
    override val parent: TickableLawnObject,
    override val trait: GenericAnimationTickerTrait
) : ObjectTraitInstance,
    TickTraitListener
{
    private val animationSpeed
        get() = if (parent is AliveLawnObject)
                    parent.getStat(1.0, EffectModifierType.get("speed"))
                else 1.0

    override fun tick(deltaTime: Double) {
        parent.animationPlayer!!.update(deltaTime * animationSpeed)
    }
}

package me.artofluxis.mod.traits.generic

import me.artofluxis.game.effects.Effect
import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.game.objects.AliveLawnObject
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.TickableLawnObject
import me.artofluxis.game.game.objects.logic.LawnPlant
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.alive.EffectStatusTraitListener
import me.artofluxis.game.trait.events.general.TickTraitListener
import me.artofluxis.mod.Util
import me.artofluxis.mod.traits.plants.generic.EffectPlantLogicTrait

class GenericAnimationTickerInstance(
    override val parent: TickableLawnObject,
    override val trait: GenericAnimationTickerTrait
) : TraitInstance(parent, trait),
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

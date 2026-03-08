package me.artofluxis.mod.traits.plants

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnPlant
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.alive.DamagedTraitListener
import me.artofluxis.game.trait.events.alive.DeathTraitListener

class ToughnessPlantLogicInstance(
    override val parent: LawnPlant,
    override val trait: ToughnessPlantLogicTrait
) : TraitInstance(parent, trait),
    DamagedTraitListener,
    DeathTraitListener
{
    var toughness = trait.toughness

    override fun damagedBy(damager: LawnObject, damage: Double) {
        toughness -= damage

        if (toughness <= 0.0) parent.death(damager)
    }

    override fun onObjectDeath(killer: LawnObject) {
        parent.scene.removeObject(parent)
    }
}

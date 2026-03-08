package me.artofluxis.mod.traits.zombies.generic

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.alive.DamagedTraitListener
import me.artofluxis.game.trait.events.alive.DeathTraitListener

class ToughnessZombieLogicInstance(
    override val parent: LawnZombie,
    override val trait: ToughnessZombieLogicTrait
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

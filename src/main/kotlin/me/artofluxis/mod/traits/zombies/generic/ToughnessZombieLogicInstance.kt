package me.artofluxis.mod.traits.zombies.generic

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.mod.trait.ObjectTraitInstance
import me.artofluxis.game.mod.trait.TraitInstance
import me.artofluxis.mod.extensions.DamageableLawnObjectExtensions.death
import me.artofluxis.mod.listeners.alive.DamagedTraitListener
import me.artofluxis.mod.listeners.alive.DeathTraitListener

class ToughnessZombieLogicInstance(
    override val parent: LawnZombie,
    override val trait: ToughnessZombieLogicTrait
) : ObjectTraitInstance,
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

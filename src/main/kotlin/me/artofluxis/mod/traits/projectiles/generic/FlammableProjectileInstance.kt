package me.artofluxis.mod.traits.projectiles.generic

import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.general.TickTraitListener

class FlammableProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: FlammableProjectileTrait
) : TraitInstance(parent, trait),
    TickTraitListener
{
    override fun tick(deltaTime: Double) {
        // check if current tile is torchwood
    }
}

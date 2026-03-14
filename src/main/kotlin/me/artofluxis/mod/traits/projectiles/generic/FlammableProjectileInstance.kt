package me.artofluxis.mod.traits.projectiles.generic

import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.game.mod.trait.ObjectTraitInstance
import me.artofluxis.mod.listeners.generic.TickTraitListener

class FlammableProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: FlammableProjectileTrait
) : ObjectTraitInstance,
    TickTraitListener
{
    override fun tick(deltaTime: Double) {
        // check if current tile is torchwood
    }
}

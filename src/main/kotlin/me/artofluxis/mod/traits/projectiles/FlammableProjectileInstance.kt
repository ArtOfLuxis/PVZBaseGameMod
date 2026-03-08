package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.trait.*
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

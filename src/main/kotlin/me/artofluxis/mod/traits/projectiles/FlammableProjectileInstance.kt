package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.trait.*

class FlammableProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: FlammableProjectileTrait
) : TraitInstance(parent, trait)

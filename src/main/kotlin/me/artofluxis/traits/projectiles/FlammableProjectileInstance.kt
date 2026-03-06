package me.artofluxis.traits.projectiles

import game.objects.logic.*
import trait.*

class FlammableProjectileInstance(
    override val parent: LawnProjectile,
    override val trait: FlammableProjectileTrait
) : TraitInstance(parent, trait) {

}

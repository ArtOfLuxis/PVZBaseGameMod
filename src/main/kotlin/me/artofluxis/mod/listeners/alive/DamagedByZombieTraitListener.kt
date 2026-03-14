package me.artofluxis.game.mod.trait.events.alive

import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.mod.trait.TraitTrigger

interface DamagedByZombieTraitListener: TraitTrigger {
    fun damagedByZombie(zombie: LawnZombie, damage: Double)
}

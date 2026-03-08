package me.artofluxis.mod.listeners

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.trait.*

interface EnteredTileTraitListener: TraitTrigger {
    fun entered(obj: LawnObject)
}

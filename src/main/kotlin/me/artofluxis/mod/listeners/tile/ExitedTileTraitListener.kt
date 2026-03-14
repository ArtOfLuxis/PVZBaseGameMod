package me.artofluxis.mod.listeners.tile

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.mod.trait.TraitTrigger

interface ExitedTileTraitListener: TraitTrigger {
    fun exited(obj: LocationalLawnObject)
}

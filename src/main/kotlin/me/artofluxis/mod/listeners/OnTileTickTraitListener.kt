package me.artofluxis.mod.listeners

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.trait.*

interface OnTileTickTraitListener: TraitTrigger {
    fun tickOnTile(objects: HashSet<LawnObject>, deltaTime: Double)
}

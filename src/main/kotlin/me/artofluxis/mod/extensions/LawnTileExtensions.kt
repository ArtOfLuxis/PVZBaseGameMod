package me.artofluxis.mod.extensions

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.mod.listeners.alive.ProjectileHitObjectTraitListener
import me.artofluxis.mod.listeners.tile.EnteredTileTraitListener
import me.artofluxis.mod.listeners.tile.ExitedTileTraitListener
import me.artofluxis.mod.listeners.tile.OnTileTickTraitListener

object LawnTileExtensions {
    fun LawnTile.objectEntered(obj: LocationalLawnObject) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is EnteredTileTraitListener) trait.entered(obj)
        }
    }

    fun LawnTile.objectExited(obj: LocationalLawnObject) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is ExitedTileTraitListener) trait.exited(obj)
        }
    }

    fun LawnTile.tickOnTile(objects: HashSet<LocationalLawnObject>, deltaTime: Double) {
        val snapshot = this.getTraitsSnapshot()
        for (trait in snapshot) {
            if (trait is OnTileTickTraitListener) trait.tickOnTile(objects, deltaTime)
        }
    }
}
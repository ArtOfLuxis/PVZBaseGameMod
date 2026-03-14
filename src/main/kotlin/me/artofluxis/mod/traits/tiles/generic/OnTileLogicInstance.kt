package me.artofluxis.mod.traits.tiles.generic

import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.mod.trait.ObjectTraitInstance
import me.artofluxis.mod.extensions.LawnTileExtensions.objectEntered
import me.artofluxis.mod.extensions.LawnTileExtensions.objectExited
import me.artofluxis.mod.extensions.LawnTileExtensions.tickOnTile
import me.artofluxis.mod.listeners.generic.TickTraitListener
import me.artofluxis.mod.listeners.tile.EnteredTileTraitListener
import me.artofluxis.mod.listeners.tile.ExitedTileTraitListener
import me.artofluxis.mod.listeners.tile.OnTileTickTraitListener

class OnTileLogicInstance(
    override val parent: LawnTile,
    override val trait: OnTileLogicTrait
) : ObjectTraitInstance,
    TickTraitListener
{
    var tileTimer = 0.0
    val currentlyOnTile = hashSetOf<LocationalLawnObject>()

    override fun tick(deltaTime: Double) {
        tileTimer += deltaTime
        if (tileTimer < 0.1)
            return

        val traits = parent.getTraitsSnapshot()

        // add newly entered objects
        trait.tileHitbox.findIntersectingObjects(parent) { obj, _ -> obj !in currentlyOnTile }
            .forEach { obj ->
                currentlyOnTile.add(obj)
                parent.objectEntered(obj)
            }

        // tick all objects currently on the tile
        if (currentlyOnTile.isNotEmpty()) {
            parent.tickOnTile(currentlyOnTile, tileTimer)
        }

        // remove objects that left the tile
        val iter = currentlyOnTile.iterator()
        while (iter.hasNext()) {
            val obj = iter.next()
            if (!obj.isIntersecting(trait.tileHitbox, parent)) {
                parent.objectExited(obj)
                iter.remove()
            }
        }

        tileTimer = 0.0
    }
}

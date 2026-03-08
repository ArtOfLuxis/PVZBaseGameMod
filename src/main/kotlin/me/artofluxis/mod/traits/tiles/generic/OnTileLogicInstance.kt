package me.artofluxis.mod.traits.tiles.generic

import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.general.TickTraitListener
import me.artofluxis.mod.listeners.EnteredTileTraitListener
import me.artofluxis.mod.listeners.ExitedTileTraitListener
import me.artofluxis.mod.listeners.OnTileTickTraitListener

class OnTileLogicInstance(
    override val parent: LawnTile,
    override val trait: OnTileLogicTrait
) : TraitInstance(parent, trait),
    TickTraitListener
{
    var tileTimer = 0.0
    val currentlyOnTile = hashSetOf<LawnObject>()

    override fun tick(deltaTime: Double) {
        tileTimer += deltaTime
        if (tileTimer < 0.1)
            return

        val traits = parent.getTraitsSnapshot()

        // add newly entered objects
        trait.tileHitbox.findIntersectingObjects(parent) { obj, _ -> obj !in currentlyOnTile }
            .forEach { obj ->
                currentlyOnTile.add(obj)
                for (traitInstance in traits) {
                    if (traitInstance is EnteredTileTraitListener) traitInstance.entered(obj)
                }
            }

        // tick all objects currently on the tile
        if (currentlyOnTile.isNotEmpty()) {
            for (traitInstance in traits) {
                if (traitInstance is OnTileTickTraitListener)
                    traitInstance.tickOnTile(currentlyOnTile, tileTimer)
            }
        }

        // remove objects that left the tile
        val iter = currentlyOnTile.iterator()
        while (iter.hasNext()) {
            val obj = iter.next()
            if (!obj.isIntersecting(trait.tileHitbox, parent)) {
                for (traitInstance in traits) {
                    if (traitInstance is ExitedTileTraitListener) traitInstance.exited(obj)
                }
                iter.remove()
            }
        }

        tileTimer = 0.0
    }
}

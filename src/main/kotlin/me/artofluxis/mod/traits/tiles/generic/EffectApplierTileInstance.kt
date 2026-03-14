package me.artofluxis.mod.traits.tiles.generic

import me.artofluxis.game.game.objects.AliveLawnObject
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.mod.trait.ObjectTraitInstance
import me.artofluxis.mod.extensions.AliveLawnObjectExtensions.applyEffect
import me.artofluxis.mod.listeners.tile.EnteredTileTraitListener
import me.artofluxis.mod.listeners.tile.ExitedTileTraitListener
import me.artofluxis.mod.listeners.tile.OnTileTickTraitListener

class EffectApplierTileInstance(
    override val parent: LawnTile,
    override val trait: EffectApplierTileTrait
) : ObjectTraitInstance,
    OnTileTickTraitListener,
    EnteredTileTraitListener,
    ExitedTileTraitListener
{
    var applyTimer = 0.0

    override fun tickOnTile(objects: HashSet<LocationalLawnObject>, deltaTime: Double) {
        applyTimer += deltaTime
        if (applyTimer < 0.2)
            return
        applyTimer = 0.0

        objects.forEach {
            if (it is AliveLawnObject) it.applyEffect(trait.effect, trait.effectTime)
        }
    }

    override fun entered(obj: LocationalLawnObject) {
        if (obj is AliveLawnObject) obj.applyEffect(trait.effect, trait.effectTime)
    }

    override fun exited(obj: LocationalLawnObject) {
        if (obj is AliveLawnObject) obj.applyEffect(trait.effect, trait.effectTime)
    }
}

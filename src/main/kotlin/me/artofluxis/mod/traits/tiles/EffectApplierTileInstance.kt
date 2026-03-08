package me.artofluxis.mod.traits.tiles

import me.artofluxis.game.game.objects.AliveLawnObject
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.registries.EffectRegistry
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.mod.listeners.EnteredTileTraitListener
import me.artofluxis.mod.listeners.ExitedTileTraitListener
import me.artofluxis.mod.listeners.OnTileTickTraitListener

class EffectApplierTileInstance(
    override val parent: LawnTile,
    override val trait: EffectApplierTileTrait
) : TraitInstance(parent, trait),
    OnTileTickTraitListener,
    EnteredTileTraitListener,
    ExitedTileTraitListener
{
    var applyTimer = 0.0

    override fun tickOnTile(objects: HashSet<LawnObject>, deltaTime: Double) {
        applyTimer += deltaTime
        if (applyTimer < 0.2)
            return
        applyTimer = 0.0

        objects.forEach {
            if (it is AliveLawnObject) it.applyEffect(trait.effect, trait.effectTime)
        }
    }

    override fun entered(obj: LawnObject) {
        if (obj is AliveLawnObject) obj.applyEffect(trait.effect, trait.effectTime)
    }

    override fun exited(obj: LawnObject) {
        if (obj is AliveLawnObject) obj.applyEffect(trait.effect, trait.effectTime)
    }
}

package me.artofluxis.mod.traits.tiles

import me.artofluxis.game.game.objects.AliveLawnObject
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.mod.traits.projectiles.EffectApplierProjectileTrait

class EffectApplierTileInstance(
    override val parent: AliveLawnObject,
    override val trait: EffectApplierProjectileTrait
) : TraitInstance(parent, trait)

// cant make tile traits with current trait and tile implementations
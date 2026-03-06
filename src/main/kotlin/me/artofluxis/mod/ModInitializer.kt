package me.artofluxis.mod

import me.artofluxis.game.trait.Trait
import me.artofluxis.mod.traits.plants.CommonPlantLogicTrait
import me.artofluxis.mod.traits.plants.StraightShooterTrait
import me.artofluxis.mod.traits.projectiles.EffectApplierProjectileTrait
import me.artofluxis.mod.traits.projectiles.FlammableProjectileTrait
import me.artofluxis.mod.traits.projectiles.StraightProjectileLogicTrait
import me.artofluxis.mod.traits.tiles.EffectApplierTileTrait
import me.artofluxis.mod.traits.zombies.CommonZombieLogicTrait

@Suppress("unused")
object ModInitializer {
    @JvmStatic
    fun init() {
        // plant traits
        Trait.register("StraightShooterPlant", ::StraightShooterTrait)
        Trait.register("CommonPlantLogic", ::CommonPlantLogicTrait)

        // projectile traits
        Trait.register("StraightProjectileLogic", ::StraightProjectileLogicTrait)
        Trait.register("EffectApplierProjectile", ::EffectApplierProjectileTrait)
        Trait.register("FlammableProjectile", ::FlammableProjectileTrait)

        // zombie traits
        Trait.register("CommonZombieLogic", ::CommonZombieLogicTrait)

        // tile traits
        Trait.register("EffectApplierTile", ::EffectApplierTileTrait)
    }
}
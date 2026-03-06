package me.artofluxis

import me.artofluxis.traits.plants.CommonPlantLogicTrait
import me.artofluxis.traits.plants.StraightShooterTrait
import me.artofluxis.traits.projectiles.EffectApplierProjectileTrait
import me.artofluxis.traits.projectiles.FlammableProjectileTrait
import me.artofluxis.traits.projectiles.StraightProjectileLogicTrait
import me.artofluxis.traits.tiles.EffectApplierTileTrait
import trait.Trait
import me.artofluxis.traits.zombies.CommonZombieLogicTrait

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
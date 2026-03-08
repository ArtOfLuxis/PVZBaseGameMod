package me.artofluxis.mod

import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.effects.OperationOrder
import me.artofluxis.game.effects.OperationType
import me.artofluxis.game.trait.Trait
import me.artofluxis.mod.traits.plants.ToughnessPlantLogicTrait
import me.artofluxis.mod.traits.plants.EffectPlantLogicTrait
import me.artofluxis.mod.traits.plants.StraightShooterTrait
import me.artofluxis.mod.traits.projectiles.CollisionProjectileLogicTrait
import me.artofluxis.mod.traits.projectiles.EffectApplierProjectileTrait
import me.artofluxis.mod.traits.projectiles.EffectRemoverProjectileTrait
import me.artofluxis.mod.traits.projectiles.FlammableProjectileTrait
import me.artofluxis.mod.traits.projectiles.StraightProjectileLogicTrait
import me.artofluxis.mod.traits.tiles.EffectApplierTileTrait
import me.artofluxis.mod.traits.zombies.EffectZombieLogicTrait
import me.artofluxis.mod.traits.zombies.MovementZombieLogicTrait
import me.artofluxis.mod.traits.zombies.ToughnessZombieLogicTrait

@Suppress("unused")
object ModInitializer {
    @JvmStatic
    fun init() {
        registerTraits()
        registerEffects()
    }

    private fun registerTraits() {
        // plant traits
        Trait.register("ToughnessPlantLogic", ::ToughnessPlantLogicTrait)
        Trait.register("StraightShooterPlant", ::StraightShooterTrait)
        Trait.register("EffectPlantLogic", ::EffectPlantLogicTrait)

        // projectile traits
        Trait.register("CollisionProjectileLogic", ::CollisionProjectileLogicTrait)
        Trait.register("StraightProjectileLogic", ::StraightProjectileLogicTrait)
        Trait.register("EffectApplierProjectile", ::EffectApplierProjectileTrait)
        Trait.register("EffectRemoverProjectile", ::EffectRemoverProjectileTrait)
        Trait.register("FlammableProjectile", ::FlammableProjectileTrait)

        // zombie traits
        Trait.register("MovementZombieLogic", ::MovementZombieLogicTrait)
        Trait.register("EffectZombieLogic", ::EffectZombieLogicTrait)
        Trait.register("ToughnessZombieLogic", ::ToughnessZombieLogicTrait)

        // tile traits
        Trait.register("EffectApplierTile", ::EffectApplierTileTrait)
    }

    private fun registerEffects() {
        EffectModifierType.register("speed")

        OperationType.register("addition") { base, value -> base + value }
        OperationType.register("multiplication") { base, value -> base * value }

        OperationOrder.register("before_addition", 1)
        OperationOrder.register("addition", 2)
        OperationOrder.register("multiplication", 3)
        OperationOrder.register("after_multiplication", 4)
    }
}
package me.artofluxis.mod

import korlibs.korge.view.anchor
import korlibs.korge.view.image
import korlibs.korge.view.position
import korlibs.korge.view.scale
import me.artofluxis.game.Position
import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.effects.OperationOrder
import me.artofluxis.game.effects.OperationType
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.TickableLawnObject
import me.artofluxis.game.game.scenes.InGameScene
import me.artofluxis.game.mod.GameRegistry
import me.artofluxis.game.mod.trait.Trait
import me.artofluxis.mod.listeners.generic.ObjectCreatedTraitListener
import me.artofluxis.mod.extensions.TickableLawnObjectExtensions.tick
import me.artofluxis.mod.traits.generic.GenericAnimationTickerTrait
import me.artofluxis.mod.traits.plants.generic.ToughnessPlantLogicTrait
import me.artofluxis.mod.traits.plants.generic.EffectPlantLogicTrait
import me.artofluxis.mod.traits.plants.StraightShooterTrait
import me.artofluxis.mod.traits.projectiles.generic.CollisionProjectileLogicTrait
import me.artofluxis.mod.traits.projectiles.generic.EffectApplierProjectileTrait
import me.artofluxis.mod.traits.projectiles.generic.EffectRemoverProjectileTrait
import me.artofluxis.mod.traits.projectiles.generic.FlammableProjectileTrait
import me.artofluxis.mod.traits.projectiles.StraightProjectileLogicTrait
import me.artofluxis.mod.traits.tiles.generic.EffectApplierTileTrait
import me.artofluxis.mod.traits.tiles.generic.OnTileLogicTrait
import me.artofluxis.mod.traits.zombies.generic.EffectZombieLogicTrait
import me.artofluxis.mod.traits.zombies.generic.MovementZombieLogicTrait
import me.artofluxis.mod.traits.zombies.generic.ToughnessZombieLogicTrait

@Suppress("unused")
object ModInitializer {
    @JvmStatic
    fun init() {
        registerTraits()
        registerEffects()
        registerGameLoop()
        registerObjectHandling()
    }

    private fun registerTraits() {
        // generic traits
        Trait.register("GenericAnimationTicker", ::GenericAnimationTickerTrait)

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
        Trait.register("OnTileLogic", ::OnTileLogicTrait)
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

    private fun registerGameLoop() {
        GameRegistry.registerGameLoop { deltaTime, scene ->
            if (scene.pendingObjects.isNotEmpty()) {
                scene.lawnObjects.addAll(scene.pendingObjects)
                scene.pendingObjects.clear()
            }

            if (scene.pendingToDeleteObjects.isNotEmpty()) {
                scene.lawnObjects.removeAll { it in scene.pendingToDeleteObjects }
                scene.pendingToDeleteObjects.clear()
            }

            scene.lawn.tick(deltaTime)

            scene.lawnObjects.forEach { obj ->
                if (obj is TickableLawnObject) obj.tick(deltaTime)
            }
        }
    }

    private fun registerObjectHandling() {
        GameRegistry.registerPutObject {
                scene: InGameScene,
                obj: LocationalLawnObject,
                anchorX: Double,
                anchorY: Double,
                scale: Double ->

            val totalScale = obj.scale() * scale

            obj.pos = Position(
                obj.pos.x + scene.lawnType.tileSize.first * obj.offset().first * totalScale,
                obj.pos.y + scene.lawnType.tileSize.second * obj.offset().second * totalScale
            )
            val pos = obj.pos

            scene.pendingObjects.add(obj)

            obj.animationPlayer?.parent = obj

            val frame = obj.animationPlayer?.frame()


            if (frame != null) {
                obj.setNewImage(scene.sContainer.image(frame) {
                    anchor(anchorX, anchorY)
                    scale(totalScale)

                    position(pos.x, pos.y)
                    smoothing = false
                    zIndex = 10.0
                })
            }

            if (obj is ObjectCreatedTraitListener) obj.onCreation()
        }

        GameRegistry.registerRemoveObject { scene: InGameScene, obj: LocationalLawnObject ->
            require(obj in scene.lawnObjects) {
                "Tried to delete a lawn object that doesn't exist or is still pending"
            }

            if (obj is TickableLawnObject) {
                obj.destroyTraits()
            }

            obj.image?.removeFromParent()
            obj.image = null

            scene.pendingToDeleteObjects.add(obj)
        }
    }
}
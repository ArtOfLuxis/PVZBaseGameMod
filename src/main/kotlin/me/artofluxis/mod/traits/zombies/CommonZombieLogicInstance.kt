package me.artofluxis.mod.traits.zombies

import Position
import effects.Effect
import effects.EffectModifierType
import game.objects.logic.LawnZombie
import korlibs.math.geom.Vector2D
import me.artofluxis.mod.Util
import trait.TraitInstance
import trait.events.alive.EffectStatusTraitListener
import trait.events.alive.TickTraitListener
import kotlin.random.Random

class CommonZombieLogicInstance(
    override val parent: LawnZombie,
    override val trait: CommonZombieLogicTrait
) : TraitInstance(parent, trait),
    EffectStatusTraitListener,
    TickTraitListener
{
    private val baseSpeed = trait.speed + Random.nextDouble(0.0, trait.additionalSpeed)
    private val actualSpeed
        get() = parent.getStat(baseSpeed, EffectModifierType.SPEED)

    override fun tick(deltaTime: Double) {
        val lawnType = parent.scene.lawnType
        val tileSize = lawnType.tileSize
        if (parent.pos.x >= lawnType.lawnUpperLeftCorner.first + tileSize.first * (lawnType.columns + 5)) {
            parent.scene.removeObject(parent)
            return
        }

        parent.pos = Position(
            parent.pos.x - parent.scene.lawnType.tileSize.first * deltaTime * actualSpeed,
            parent.pos.y
        )
        parent.image!!.pos = Vector2D(parent.pos.x, parent.pos.y)

        val effects = parent.effects.toList()
        for ((effect, effectTimer) in effects) {
            if (effectTimer.isExpired()) {
                removedEffect(effect)
            }
        }
    }

    override fun appliedEffect(effect: Effect, effectTime: Double) {
        Util.applyEffect(parent, effect, effectTime)
    }

    override fun removedEffect(effect: Effect) {
        Util.removeEffect(parent, effect)
    }
}

package me.artofluxis.traits.zombies

import Position
import effects.*
import effects.visual.*
import game.objects.logic.*
import korlibs.math.geom.Vector2D
import trait.*
import trait.events.alive.*
import kotlin.random.*

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
        val currentTime = parent.effects[effect]?.remaining() ?: 0.0
        if (effectTime > currentTime)
            parent.effects[effect] = Timer.start(effectTime)

        effect.visuals.forEach {
            when (it) {
                is EffectVisualTint -> {
                    parent.highlightFilter.colors.add(it.color)
                }
            }
        }
    }

    override fun removedEffect(effect: Effect) {
        parent.effects.remove(effect)
        effect.visuals.forEach {
            when (it) {
                is EffectVisualTint -> {
                    parent.highlightFilter.colors.remove(it.color)
                }
            }
        }
    }
}

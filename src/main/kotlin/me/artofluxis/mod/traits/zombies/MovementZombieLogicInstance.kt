package me.artofluxis.mod.traits.zombies

import korlibs.math.geom.Vector2D
import me.artofluxis.game.Position
import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.game.objects.logic.LawnZombie
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.general.TickTraitListener
import kotlin.random.Random

class MovementZombieLogicInstance(
    override val parent: LawnZombie,
    override val trait: MovementZombieLogicTrait
) : TraitInstance(parent, trait),
    TickTraitListener
{
    private val baseSpeed = trait.speed + Random.nextDouble(0.0, trait.additionalSpeed)
    private val actualSpeed
        get() = parent.getStat(baseSpeed, EffectModifierType.get("speed"))

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
    }
}

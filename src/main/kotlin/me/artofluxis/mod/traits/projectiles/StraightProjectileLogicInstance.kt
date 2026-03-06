package me.artofluxis.mod.traits.projectiles

import me.artofluxis.game.Position
import me.artofluxis.game.effects.EffectModifierType
import me.artofluxis.game.game.objects.*
import me.artofluxis.game.game.objects.logic.*
import korlibs.math.geom.*
import me.artofluxis.game.trait.*
import me.artofluxis.game.trait.events.alive.TickTraitListener

class StraightProjectileLogicInstance(
    override val parent: LawnProjectile,
    override val trait: StraightProjectileLogicTrait
) : TraitInstance(parent, trait), TickTraitListener {
    val flyingSpeed: Double get() {
        val shooter = parent.parentShooter

        if (shooter !is AliveLawnObject)
            return 1.0

        return shooter.getStat(1.0, EffectModifierType.SPEED)
    }

    override fun tick(deltaTime: Double) {
        val lawnType = parent.scene.lawnType
        val tileSize = lawnType.tileSize
        if (parent.pos.x >= lawnType.lawnUpperLeftCorner.first + tileSize.first * (lawnType.columns + 5)) {
            parent.scene.removeObject(parent)
            return
        }

        val projectileSpeed = (parent.scene.lawnType.tileSize.first * deltaTime * trait.speed) * flyingSpeed
        parent.pos = Position(
            parent.pos.x + projectileSpeed,
            parent.pos.y
        )
        parent.image!!.pos = Vector2D(parent.pos.x, parent.pos.y)

        val hitObject = parent.findIntersectingObjects { lawnObject, _ ->
            lawnObject.team != parent.team
                && lawnObject.row == parent.row
                && lawnObject is AliveLawnObject
        }.firstOrNull() as? AliveLawnObject

        if (hitObject != null) {
            parent.projectileHitObject(hitObject)
            hitObject.hitByProjectile(parent)
            parent.scene.removeObject(parent)
        }
    }
}

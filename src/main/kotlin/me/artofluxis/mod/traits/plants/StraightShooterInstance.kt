package me.artofluxis.mod.traits.plants

import me.artofluxis.game.Position
import me.artofluxis.game.effects.*
import me.artofluxis.game.game.objects.logic.*
import me.artofluxis.game.trait.*
import me.artofluxis.game.trait.events.general.TickTraitListener
import kotlin.random.*

class StraightShooterInstance(
    override val parent: LawnPlant,
    override val trait: StraightShooterTrait
) : TraitInstance(parent, trait),
    TickTraitListener
{
    private var attackTimer = 0.0
    private var projectileTimer = 0.0
    private var projectilesLeft = 0

    private val fireRateMultiplier
        get() = parent.getStat(1.0, EffectModifierType.get("speed"))

    override fun tick(deltaTime: Double) {
        if (projectilesLeft <= 0) {
            attackTimer += deltaTime * fireRateMultiplier

            if (attackTimer >= trait.interval) {
                attackTimer = Random.nextDouble(-trait.additionalInterval, 0.0)
                projectilesLeft = trait.projectileAmount
                projectileTimer = 0.0
            }
            return
        }

        val shouldShoot = trait.detectionHitbox.anyIntersectingObject(parent) { lawnObject, _ ->
            lawnObject.team != parent.team
        }

        if (!shouldShoot) return

        projectileTimer += deltaTime * fireRateMultiplier

        if (projectileTimer >= trait.projectileInterval) {
            projectileTimer = 0.0
            shoot()
            projectilesLeft--
        }
    }

    private fun shoot() {
        for (rowOffset in trait.attackRows) {
            spawnProjectile(rowOffset)
        }
    }

    private fun spawnProjectile(rowOffset: Int) {
        val tileSize = parent.scene.lawnType.tileSize
        val projectile = LawnProjectile(
            Position(
                parent.pos.x +  trait.projectilePositionOffset.x              * tileSize.first  * parent.scale(),
                parent.pos.y + (trait.projectilePositionOffset.y + rowOffset) * tileSize.second * parent.scale()
            ),
            parent.row + rowOffset, parent.team, parent.scene,
            null, hashSetOf(), trait.projectile, parent
        )
        trait.projectile.traits.forEach { projectile.addTrait(it.createInstance(projectile)) }
        trait.extraProjectileTraits.forEach { projectile.addTrait(it.createInstance(projectile)) }

        parent.scene.putProjectile(projectile)
    }
}

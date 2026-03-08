package me.artofluxis.mod.traits.projectiles.generic

import me.artofluxis.game.game.objects.DamageableLawnObject
import me.artofluxis.game.game.objects.logic.LawnProjectile
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.events.general.TickTraitListener

class CollisionProjectileLogicInstance(
    override val parent: LawnProjectile,
    override val trait: CollisionProjectileLogicTrait
) : TraitInstance(parent, trait),
    TickTraitListener
{
    override fun tick(deltaTime: Double) {
        val hitObject = parent.findIntersectingObjects { lawnObject, _ ->
            lawnObject.team != parent.team
        }.firstOrNull() as? DamageableLawnObject

        if (hitObject != null) {
            parent.projectileHitObject(hitObject, trait.damage)
            hitObject.hitByProjectile(parent, trait.damage)
            parent.scene.removeObject(parent)
        }
    }
}

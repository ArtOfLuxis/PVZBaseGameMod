package me.artofluxis.mod.traits.plants

import me.artofluxis.game.Position
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnPlant
import me.artofluxis.game.game.types.ProjectileType
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.game.hitbox.Hitbox
import me.artofluxis.game.trait.Trait
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.TraitType
import me.artofluxis.mod.serializers.lazy.DoubleDeserializer
import me.artofluxis.mod.serializers.lazy.HitboxDeserializer
import me.artofluxis.mod.serializers.lazy.IntDeserializer
import me.artofluxis.mod.serializers.lazy.ListDeserializer
import me.artofluxis.mod.serializers.lazy.PositionDeserializer
import me.artofluxis.mod.serializers.lazy.ProjectileTypeDeserializer
import me.artofluxis.mod.serializers.TraitSerializer

class StraightShooterTrait(
    jsonObject: JsonObject
) : Trait(
    mapOf(
        "projectile" to ProjectileTypeDeserializer,
        "projectilePositionOffset" to PositionDeserializer,
        "interval" to DoubleDeserializer,
        "additionalInterval" to DoubleDeserializer,
        "detectionHitbox" to HitboxDeserializer,
        "projectileAmount" to IntDeserializer,
        "projectileInterval" to DoubleDeserializer,
        "attackRows" to ListDeserializer(Int.serializer()),
        "extraProjectileTraits" to ListDeserializer(TraitSerializer),
    ), TraitType.PLANT, jsonObject) {

    val projectile get() = get<ProjectileType>("projectile")
    val projectilePositionOffset get() = get<Position>("projectilePositionOffset")
    val interval get() = get<Double>("interval")
    val additionalInterval get() = get<Double>("additionalInterval")
    val detectionHitbox get() = get<Hitbox>("detectionHitbox")
    val projectileAmount get() = get<Int>("projectileAmount")
    val projectileInterval get() = get<Double>("projectileInterval")
    val attackRows get() = get<List<Int>>("attackRows")
    val extraProjectileTraits get() = get<List<Trait>>("extraProjectileTraits")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnPlant) {
            "Parent for ${this::class.simpleName} must be a ${LawnPlant::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return StraightShooterInstance(parent, this)
    }
}

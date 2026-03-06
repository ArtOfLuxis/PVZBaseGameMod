package me.artofluxis.traits.plants

import Position
import game.objects.LawnObject
import game.objects.logic.LawnPlant
import game.types.ProjectileType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject
import me.artofluxis.serializers.HitboxSerializer
import me.artofluxis.serializers.PositionSerializer
import me.artofluxis.serializers.ProjectileTypeSerializer
import trait.Trait
import trait.TraitInstance
import trait.TraitType

class StraightShooterTrait(
    jsonObject: JsonObject
) : Trait(
    mapOf(
        "projectile" to ProjectileTypeSerializer,
        "projectilePositionOffset" to PositionSerializer,
        "interval" to Double.serializer(),
        "additionalInterval" to Double.serializer(),
        "detectionHitbox" to HitboxSerializer,
        "projectileAmount" to Int.serializer(),
        "projectileInterval" to Double.serializer(),
        "attackRows" to ListSerializer(Int.serializer()),
    ),
    TraitType.PLANT
) {
    override val values = deserialize(jsonObject)

    val projectile get() = get<ProjectileType>("projectile")
    val projectilePositionOffset get() = get<Position>("projectilePositionOffset")
    val interval get() = get<Double>("interval")
    val additionalInterval get() = get<Double>("additionalInterval")
    val projectileAmount get() = get<Int>("projectileAmount")
    val projectileInterval get() = get<Double>("projectileInterval")
    val attackRows get() = get<List<Int>>("attackRows")

    override fun createInstance(parent: LawnObject): TraitInstance {
        require(parent is LawnPlant) {
            "Parent for ${this::class.simpleName} must be a ${LawnPlant::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return StraightShooterInstance(parent, this)
    }
}

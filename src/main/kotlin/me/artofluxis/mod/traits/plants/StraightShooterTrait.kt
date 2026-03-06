package me.artofluxis.mod.traits.plants

import me.artofluxis.game.Position
import me.artofluxis.game.game.objects.LawnObject
import me.artofluxis.game.game.objects.logic.LawnPlant
import me.artofluxis.game.game.types.ProjectileType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject
import me.artofluxis.game.trait.Trait
import me.artofluxis.game.trait.TraitInstance
import me.artofluxis.game.trait.TraitType

class StraightShooterTrait(
    jsonObject: JsonObject
) : Trait(
    mapOf(
        "projectile" to me.artofluxis.mod.serializers.ProjectileTypeSerializer,
        "projectilePositionOffset" to me.artofluxis.mod.serializers.PositionSerializer,
        "interval" to Double.serializer(),
        "additionalInterval" to Double.serializer(),
        "detectionHitbox" to me.artofluxis.mod.serializers.HitboxSerializer,
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

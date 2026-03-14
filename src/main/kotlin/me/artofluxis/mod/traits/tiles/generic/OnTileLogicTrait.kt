package me.artofluxis.mod.traits.tiles.generic

import kotlinx.serialization.json.*
import me.artofluxis.game.game.hitbox.Hitbox
import me.artofluxis.game.game.objects.LocationalLawnObject
import me.artofluxis.game.game.objects.logic.LawnTile
import me.artofluxis.game.mod.trait.*
import me.artofluxis.mod.serializers.lazy.HitboxDeserializer

class OnTileLogicTrait(
    jsonObject: JsonObject
) : Trait(hashMapOf(
    "tileHitbox" to HitboxDeserializer
), TraitType.TILE, jsonObject) {
    val tileHitbox get() = get<Hitbox>("tileHitbox")

    override fun createInstance(parent: LocationalLawnObject): TraitInstance {
        require(parent is LawnTile) {
            "Parent for ${this::class.simpleName} must be a ${LawnTile::class.simpleName}, found a ${parent::class.simpleName}"
        }
        return OnTileLogicInstance(parent, this)
    }
}

package me.artofluxis.mod.extensions

import kotlinx.coroutines.launch
import me.artofluxis.game.game.objects.TickableLawnObject
import me.artofluxis.game.handleException
import me.artofluxis.mod.listeners.generic.TickTraitListener

object TickableLawnObjectExtensions {
    fun TickableLawnObject.tick(deltaTime: Double) {
        if (!this.shouldTick) return
        val snapshot = this.getTraitsSnapshot()

        for (trait in snapshot) {
            try {
                if (trait is TickTraitListener) trait.tick(deltaTime)
            } catch (e: Throwable) {
                scene.launch { handleException(e, "Error during trait tick") }
            }
        }
    }
}
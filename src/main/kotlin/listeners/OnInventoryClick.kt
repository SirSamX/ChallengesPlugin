package me.sirsam.challenges.listeners

import me.sirsam.challenges.guis.Challenges
import me.sirsam.challenges.guis.TimerGui
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class OnInventoryClick : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val customInvs = listOf(Challenges(), TimerGui())

        if (event.inventory.holder !in customInvs) return
        event.isCancelled = true
    }
}
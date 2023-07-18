package me.sirsam.challenges.listeners

import me.sirsam.challenges.helpers.Scoreboard
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnJoin : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        Scoreboard().sendScoreboard(player)
    }
}
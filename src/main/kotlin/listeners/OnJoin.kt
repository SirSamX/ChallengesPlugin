package me.sirsam.challenges.listeners

import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnJoin : Listener {
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        //player.scoreboard = emanuels scoreboard
    }
}
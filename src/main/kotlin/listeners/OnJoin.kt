package me.sirsam.challenges.listeners

import me.sirsam.challenges.Main
import me.sirsam.challenges.challanges.AllItems
import me.sirsam.challenges.helpers.Scoreboard
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scheduler.BukkitRunnable

class OnJoin : Listener {
    val plugin = Main.getPlugin()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        object : BukkitRunnable() {
            override fun run() {
                AllItems().sendBossbar(player)
                Scoreboard().sendScoreboard(player)
            }
        }.runTaskLater(plugin, 5L)
    }
}
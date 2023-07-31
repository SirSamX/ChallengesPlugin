package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.Challenge
import me.sirsam.challenges.helpers.ChallengeStatus
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLevelChangeEvent

class LevelBorder : Challenge, Listener {
    private var enabled = false
    @EventHandler
    fun worldborder(event: PlayerLevelChangeEvent) {
        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE && !enabled) return
        var size = 1.0
        for (player in Bukkit.getOnlinePlayers()) {
            size += player.level
            size /= Bukkit.getOnlinePlayers().size
        }
        event.player.world.worldBorder.size = size
        event.player.sendMessage(Component.text("Test"))

    }

    override fun onEnable() {
        enabled = true

    }

    override fun onDisable() {
        enabled = false
    }
}
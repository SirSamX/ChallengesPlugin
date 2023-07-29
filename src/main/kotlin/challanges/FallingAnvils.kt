package me.sirsam.challenges.challanges

import me.sirsam.challenges.Main
import me.sirsam.challenges.helpers.Challenge
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask


class FallingAnvils : Challenge {
    private var loopTask: BukkitTask? = null

    override fun onEnable() {
        loopTask = Loop().runTaskTimer(Main.getPlugin(), 100, 10)
    }

    override fun onDisable() {
        loopTask?.cancel()
    }

    private class Loop : BukkitRunnable() {
        override fun run() {
            for (player in Bukkit.getOnlinePlayers()) {
                if (player.gameMode !in listOf(GameMode.SURVIVAL, GameMode.ADVENTURE)) continue
                val loc = player.location.clone()
                loc.y += 35
                player.world.getBlockAt(loc).type = Material.ANVIL
            }
        }
    }
}
package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player

class AllItems {
    fun newItem(player: Player) {
        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE) return
        val item = Material.values().random()
        val loc = player.location
        loc.y += 2.5
        //val display =
        //player.world.spawn(loc, ItemDisplay)
    }

    fun getBossbar(): BossBar {
        val item = Material.values().random()
        val bossBar = Bukkit.createBossBar(item.name, BarColor.RED, BarStyle.SOLID)

        return bossBar
    }

    fun showBossbar(player: Player) {
        //player.showBossBar(getBossbar())

    }
}
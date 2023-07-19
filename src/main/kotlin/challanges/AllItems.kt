package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
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
        val bossbar = BossBar.bossBar(Component.text(item.name), 100f, BossBar.Color.PURPLE, BossBar.Overlay.PROGRESS)
        return bossbar
    }

    fun sendBossbar(player: Player) {
        player.showBossBar(getBossbar())
    }

}
package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.Main
import me.sirsam.challenges.helpers.Challenge
import me.sirsam.challenges.helpers.ChallengeStatus
import me.sirsam.challenges.helpers.Utilities
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class AllItems : Challenge, Listener {
    private var items: MutableMap<Player, Material> = mutableMapOf()

    fun newItem(player: Player) {
        items[player] = Material.values().random()

        sendBossbar(player)
        player.playerListName(Component.text(player.name).append(Component.text(" ")).append(ItemStack(items[player]!!).displayName().color(NamedTextColor.LIGHT_PURPLE)))
        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE || items[player] == null) return

        player.sendEquipmentChange(player, EquipmentSlot.HEAD, ItemStack(items[player]!!))
    }

    fun getItem(player: Player): Material? {
        return items[player]
    }

    fun getBossbar(player: Player): BossBar {
        val item = ItemStack(items[player]!!)
        return BossBar.bossBar(Component.text("Current Item: ", NamedTextColor.DARK_PURPLE).append(item.displayName().color(NamedTextColor.LIGHT_PURPLE)), 1f, BossBar.Color.PURPLE, BossBar.Overlay.PROGRESS)
    }

    fun sendBossbar(player: Player) {
        val bossbars = Bukkit.getBossBars()
        for (bossbar in bossbars) {
            bossbar.removePlayer(player)
        }
        player.showBossBar(getBossbar(player))
    }

    @EventHandler
    fun itemCollected(event: EntityPickupItemEvent) {
        if (event.entity !is Player) return
        if (!Utilities().isChallengeEnabled(ChallengeManager.ALL_ITEMS, Main.getPlugin())) return
        val player = event.entity as Player
        if (items[player] == null) {
            newItem(player)
        } else {
            if (event.item.itemStack.type != items[player]) return
            newItem(player)
        }
    }
}
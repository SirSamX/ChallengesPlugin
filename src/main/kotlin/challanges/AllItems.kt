package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.BlockDisplay
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class AllItems {
    private var items: MutableMap<Player, Material> = mutableMapOf()

    fun newItem(player: Player) {
        items[player] = Material.values().random()

        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE || items[player] == null) return
        val loc = player.location
        loc.y += 2.5

        if (items[player]!!.isBlock) {
            val display = player.world.spawn(loc, BlockDisplay::class.java)
            display.block = Bukkit.createBlockData(items[player]!!)
        } else {
            val display = player.world.spawn(loc, ItemDisplay::class.java)
            display.itemStack = ItemStack(items.getValue(player))
        }

    }

    fun getItem(player: Player): Material? {
        return items[player]
    }
}
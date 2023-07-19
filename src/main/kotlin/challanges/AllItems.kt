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
    private var item: MutableMap<Player, Material> = mutableMapOf()

    fun newItem(player: Player) {
        item[player] = Material.values().random()
        item[player]
        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE || item[player] == null) return
        val loc = player.location
        loc.y += 2.5

        if (item[player]!!.isBlock) {
            val display = player.world.spawn(loc, BlockDisplay::class.java)
            display.block = Bukkit.createBlockData(item[player]!!)
        } else {
            val display = player.world.spawn(loc, ItemDisplay::class.java)
            display.itemStack = ItemStack(item.getValue(player))
        }

    }

    fun getItem(player: Player): Material? {
        return item[player]
    }
}
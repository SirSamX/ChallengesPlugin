package me.sirsam.challenges.guis

import me.sirsam.challenges.Main
import me.sirsam.challenges.challanges.ChallengeManager
import me.sirsam.challenges.helpers.ItemBuilder
import me.sirsam.challenges.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class Challenges: InventoryHolder {
    override fun getInventory(): Inventory {
        val inv = Bukkit.createInventory(this, 36, Component.text("Challenges", NamedTextColor.DARK_PURPLE))

        ChallengeManager.values().forEachIndexed { index, challenge ->
            val enabled = Utilities().isChallengeEnabled(challenge, Main.getPlugin())
            inv.setItem(index, ItemBuilder(if (enabled) Material.GREEN_CONCRETE else challenge.item, challenge.challengeName, challenge.description, 1, Utilities().isChallengeEnabled(challenge, Main.getPlugin())).item())
        }

        return inv
    }
}
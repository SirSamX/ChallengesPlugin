package me.sirsam.challenges.guis

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import me.sirsam.challenges.helpers.ItemBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class TimerGui: InventoryHolder {
    private val timer = ChallengeTimer.timer

    override fun getInventory(): Inventory {
        val inv = Bukkit.createInventory(this, 27, Component.text("Timer", NamedTextColor.DARK_PURPLE))

        val backgroundMaterial = when (timer.getStatus()) {
            ChallengeStatus.ACTIVE -> Material.GREEN_STAINED_GLASS_PANE
            ChallengeStatus.PAUSED -> Material.YELLOW_STAINED_GLASS_PANE
            ChallengeStatus.STOPPED -> Material.RED_STAINED_GLASS_PANE
        }

        for (i in 0 until inv.size) {
            inv.setItem(i, ItemBuilder(backgroundMaterial, Component.text("")).item())
        }

        inv.setItem(11, ItemBuilder(Material.GREEN_CONCRETE, Component.text("Start", NamedTextColor.GREEN)).item())
        inv.setItem(13, ItemBuilder(Material.YELLOW_CONCRETE, Component.text("Pause", NamedTextColor.YELLOW)).item())
        inv.setItem(15, ItemBuilder(Material.RED_CONCRETE, Component.text("Reset" , NamedTextColor.RED)).item())
        inv.setItem(26, ItemBuilder(Material.ENDER_EYE, Component.text(if (timer.isHidden()) "Hidden" else "Shown", if (timer.isHidden()) NamedTextColor.RED else NamedTextColor.GREEN)).item())
        inv.setItem(18, ItemBuilder(Material.BOOK, Component.text("Challenges", NamedTextColor.BLUE)).item())

        return inv
    }
}
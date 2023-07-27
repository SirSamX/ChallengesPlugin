package me.sirsam.challenges.guis

import me.sirsam.challenges.helpers.Gui
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class Challenges: InventoryHolder {
    private val gui = Gui()

    override fun getInventory(): Inventory {
        val inv = Bukkit.createInventory(this, 36, Component.text("Challenges", NamedTextColor.DARK_PURPLE))

        inv.setItem(0, gui.item(Material.DIAMOND_HELMET, Component.text("AllItems", NamedTextColor.GREEN)))
        inv.setItem(27, gui.item(Material.SPRUCE_SIGN, Component.text("Main Menu", NamedTextColor.DARK_PURPLE)))

        return inv
    }
}
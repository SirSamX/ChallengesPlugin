package me.sirsam.challenges.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Gui {
    fun item(material: Material, name: Component, lore: MutableList<Component>? = null, amount: Int = 1): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta

        meta.displayName(name.decoration(TextDecoration.ITALIC, false))
        meta.lore(lore)
        item.itemMeta = meta

        return item
    }
}
package me.sirsam.challenges.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ItemBuilder(var material: Material, var name: Component, var lore: MutableList<Component>? = null, var amount: Int = 1, var glint: Boolean = false) {
    init {
        if (!lore.isNullOrEmpty()) {
            lore!!.forEachIndexed { index, line ->
                lore!![index] = line.decoration(TextDecoration.ITALIC, false)
            }
        }
    }

    fun item(): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta

        meta.displayName(name.decoration(TextDecoration.ITALIC, false))
        meta.lore(lore)
        item.itemMeta = meta
        if (glint) {
            item.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1)
        }

        return item
    }
}
package me.sirsam.challenges.commands

import me.sirsam.challenges.PvpDimension
import me.sirsam.challenges.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Kit : CommandExecutor, TabCompleter {
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args == null  || args.isEmpty()) { utils.formattingErrorMessage(sender); return true }
        when (args[0].lowercase()) {
            "warrior" -> {
                sender.sendMessage(Component.text("Warrior kit is enabled!", NamedTextColor.GREEN))
                val loc = Location(PvpDimension.world, 0.0, -60.0, 0.0)
                sender.teleport(loc)


                sender.inventory.clear()
                val itemNetheriteSword = ItemStack(Material.NETHERITE_SWORD)
                itemNetheriteSword.addEnchantment(Enchantment.DAMAGE_ALL, 5)
                itemNetheriteSword.addEnchantment(Enchantment.FIRE_ASPECT, 2)

                val itemDiamondHelmet = ItemStack(Material.DIAMOND_HELMET)
                itemDiamondHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                itemDiamondHelmet.addEnchantment(Enchantment.THORNS, 2)

                val itemDiamondChestplate = ItemStack(Material.DIAMOND_CHESTPLATE)
                itemDiamondChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                itemDiamondChestplate.addEnchantment(Enchantment.THORNS, 2)

                val itemDiamondLeggins = ItemStack(Material.DIAMOND_LEGGINGS)
                itemDiamondLeggins.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                itemDiamondLeggins.addEnchantment(Enchantment.THORNS, 2)

                val itemDiamondBoots = ItemStack(Material.DIAMOND_BOOTS)
                itemDiamondBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                itemDiamondBoots.addEnchantment(Enchantment.THORNS, 2)

                sender.inventory.setItemInMainHand(itemNetheriteSword)
                sender.inventory.helmet = itemDiamondHelmet
                sender.inventory.chestplate = itemDiamondChestplate
                sender.inventory.leggings = itemDiamondLeggins
                sender.inventory.boots = itemDiamondBoots
                sender.inventory.addItem(ItemStack(Material.GOLDEN_CARROT, 20))
            }

            "stop" -> {
                sender.inventory.clear()
                sender.sendMessage(Component.text("Kit disabled!", NamedTextColor.RED))
            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf("warrior", "stop")

    }
}
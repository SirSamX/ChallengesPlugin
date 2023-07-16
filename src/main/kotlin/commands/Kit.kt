package me.sirsam.challenges.commands

import jdk.jshell.execution.Util
import me.sirsam.challenges.helpers.Utilities
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Kit : CommandExecutor, TabCompleter {
    val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args == null  || args.isEmpty()) { utils.formattingErrorMessage(sender); return true }
        when (args[0].lowercase()) {
            "warrior" -> {
                val itemNetheriteSword = ItemStack(Material.NETHERITE_SWORD)
                itemNetheriteSword.addEnchantment(Enchantment.DAMAGE_ALL, 5)
                itemNetheriteSword.addEnchantment(Enchantment.FIRE_ASPECT, 2)
                sender.inventory.addItem(ItemStack(Material.DIAMOND_HELMET, 1))
                sender.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE, 1))
                sender.inventory.addItem(ItemStack(Material.DIAMOND_LEGGINGS, 1))
                sender.inventory.addItem(ItemStack(Material.DIAMOND_BOOTS, 1))
                sender.inventory.addItem(itemNetheriteSword)
            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String>? {
        return mutableListOf("warrior")

    }
}
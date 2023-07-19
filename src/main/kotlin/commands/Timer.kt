package me.sirsam.challenges.commands

import me.sirsam.challenges.guis.Challenges
import me.sirsam.challenges.guis.TimerGui
import me.sirsam.challenges.helpers.Utilities
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Timer : CommandExecutor {
    private val utils = Utilities()
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        sender.openInventory(TimerGui().inventory)

        return true
    }
}
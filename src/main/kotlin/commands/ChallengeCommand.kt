package me.sirsam.challenges.commands

import me.sirsam.challenges.Main
import me.sirsam.challenges.challanges.ChallengeManager
import me.sirsam.challenges.guis.Challenges
import me.sirsam.challenges.helpers.Utilities
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class ChallengeCommand : CommandExecutor, TabCompleter {
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args.isNullOrEmpty()) { sender.openInventory(Challenges().inventory); return true }

        val challengeName = args[0].uppercase()
        if (args[0].uppercase() in ChallengeManager.getNames()) {
            val challenge = ChallengeManager.valueOf(challengeName)
            utils.toggleChallengeStatus(challenge, Main.getPlugin())
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return ChallengeManager.getNames().toMutableList()
    }
}
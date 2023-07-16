package me.sirsam.challenges.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TimerCompleter : TabCompleter {
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf("start", "stop", "pause", "resume", "reset", "show", "hide", "set")
    }
}
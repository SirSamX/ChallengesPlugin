package me.sirsam.challenges.commands

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import me.sirsam.challenges.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class TimerCommand : CommandExecutor, TabCompleter {
    private val timer = ChallengeTimer.timer
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args.isNullOrEmpty()) { sender.openInventory(me.sirsam.challenges.guis.TimerGui().inventory); return true }

        when (args[0].lowercase()) {
            "start", "resume" -> {
                if (timer.getStatus() == ChallengeStatus.ACTIVE) {
                    utils.sendMessage(sender, Component.text("Timer has already started!", NamedTextColor.GRAY))
                    return true
                }
                timer.setStatus(ChallengeStatus.ACTIVE)
            }

            "stop", "pause" -> {
                if (timer.getStatus() == ChallengeStatus.PAUSED) {
                    utils.sendMessage(sender, Component.text("Timer is already paused!", NamedTextColor.GRAY))
                    return true
                }
                timer.setStatus(ChallengeStatus.PAUSED)
            }

            "reset" -> {
                if (timer.getStatus() == ChallengeStatus.STOPPED) {
                    utils.sendMessage(sender, Component.text("Timer is already reset!", NamedTextColor.GRAY))
                    return true
                }
                timer.reset()
            }

            "show" -> {
                if (!timer.isHidden()) {
                    utils.sendMessage(sender, Component.text("Timer is already shown!", NamedTextColor.GRAY))
                    return true
                }
                timer.show()
            }

            "hide" -> {
                if (timer.isHidden()) {
                    utils.sendMessage(sender, Component.text("Timer is already hidden!", NamedTextColor.GRAY))
                    return true
                }
                timer.hide()
            }

            "set" -> {
                if (args.size != 2) {
                    utils.sendMessage(sender, Component.text("Usage: /timer set <time>", NamedTextColor.GRAY))
                    return true
                }
                var time: Long? = null
                try {
                    time = args[1].toLong()
                } catch (e: NumberFormatException) {
                    utils.sendMessage(sender, Component.text("Usage: /timer set <time>", NamedTextColor.GRAY))
                }
                if (time == null) return true
                timer.set(time)
            }

            "add" -> {
                if (args.size != 2) {
                    utils.sendMessage(sender, Component.text("Usage: /timer add <time>", NamedTextColor.GRAY))
                    return true
                }
                var time: Long? = null
                try {
                    time = args[1].toLong()
                } catch (e: NumberFormatException) {
                    utils.sendMessage(sender, Component.text("<time> must be numeric!", NamedTextColor.GRAY))
                }
                if (time == null) return true
                timer.add(time)
            }

            "remove" -> {
                if (args.size != 2) {
                    utils.sendMessage(sender, Component.text("Usage: /timer remove <time>", NamedTextColor.GRAY))
                    return true
                }
                var time: Long? = null
                try {
                    time = args[1].toLong()
                } catch (e: NumberFormatException) {
                    utils.sendMessage(sender, Component.text("<time> must be numeric!", NamedTextColor.GRAY))
                }
                if (time == null) return true
                timer.remove(time)
            }

            else -> { sender.openInventory(me.sirsam.challenges.guis.TimerGui().inventory) }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return if (args.isNullOrEmpty()) {
            mutableListOf("start", "stop", "pause", "resume", "reset", "show", "hide", "set", "add", "remove")
        } else if (args[0] == "set" || args[0] == "add" || args[0] == "remove") mutableListOf("<time>")
        else mutableListOf("start", "stop", "pause", "resume", "reset", "show", "hide", "set", "add", "remove")
    }
}
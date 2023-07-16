package me.sirsam.challenges.commands

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import me.sirsam.challenges.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TimerCommand : CommandExecutor {
    private val timer = ChallengeTimer.timer
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args == null  || args.isEmpty()) { sender.openInventory(me.sirsam.challenges.guis.TimerGui().inventory); return true }

        when (args[0].lowercase()) {
            "start", "resume" -> {
                if (timer.getStatus() == ChallengeStatus.ACTIVE) {
                    sender.sendMessage(utils.prefix.append(Component.text("Timer has already started!", NamedTextColor.GRAY)))
                    return true
                }
                timer.setStatus(ChallengeStatus.ACTIVE)
            }

            "stop", "pause" -> {
                if (timer.getStatus() == ChallengeStatus.PAUSED) {
                    sender.sendMessage(utils.prefix.append(Component.text("Timer is already paused!", NamedTextColor.GRAY)))
                    return true
                }
                timer.setStatus(ChallengeStatus.PAUSED)
            }

            "reset" -> {
                if (timer.getStatus() == ChallengeStatus.STOPPED) {
                    sender.sendMessage(utils.prefix.append(Component.text("Timer is already reset!", NamedTextColor.GRAY)))
                    return true
                }
                timer.setStatus(ChallengeStatus.STOPPED)
                timer.time = 0
            }

            "show" -> {
                if (!timer.isHidden()) {
                    sender.sendMessage(utils.prefix.append(Component.text("Timer is already shown!", NamedTextColor.GRAY)))
                    return true
                }
                timer.show()
            }

            "hide" -> {
                if (timer.isHidden()) {
                    sender.sendMessage(utils.prefix.append(Component.text("Timer is already hidden!", NamedTextColor.GRAY)))
                    return true
                }
                timer.hide()
            }

            "set" -> {
                if (args.size != 2) {
                    sender.sendMessage(utils.prefix.append(Component.text("Usage: /timer set <time>", NamedTextColor.GRAY)))
                    return true
                }
                var time: Int? = null
                try {
                    time = args[1].toInt()
                } catch (e: NumberFormatException) {
                    sender.sendMessage(utils.prefix.append(Component.text("Usage: /timer set <time>", NamedTextColor.GRAY)))
                }
                if (time == null) return true
                timer.time = time
                sender.sendMessage(utils.prefix.append(Component.text("Timer set to $time!", NamedTextColor.GRAY)))
            }

            else -> sender.openInventory(me.sirsam.challenges.guis.TimerGui().inventory)
        }
        return true
    }
}
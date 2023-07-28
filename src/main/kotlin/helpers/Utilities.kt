package me.sirsam.challenges.helpers

import me.sirsam.challenges.challanges.ChallengeManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Utilities {
    val prefix = Component.text("Challenges", NamedTextColor.DARK_PURPLE).append(Component.text(" >> ", NamedTextColor.LIGHT_PURPLE))

    fun sendMessage(player: Player, message: Component) { player.sendMessage(prefix.append(message)) }

    fun sendMessage(players: List<Player>, message: Component) { players.forEach { p ->  p.sendMessage(prefix.append(message)) } }

    fun broadcast(message: Component) { Bukkit.getOnlinePlayers().forEach { p -> p.sendMessage(prefix.append(message)) } }

    @Deprecated("Use Component instead of String!")
    fun broadcast(message: String) { Bukkit.getOnlinePlayers().forEach { p -> p.sendMessage(prefix.append(PlainTextComponentSerializer.plainText().deserialize(message))) } }

    fun receiveItemMessage(p: Player, a: Int, i: Component) { p.sendMessage(Component.text("You received $a ").append(i).append(Component.text("!"))) }

    fun formattingErrorMessage(p: Player) { p.sendMessage(Component.text("Formatting error, check your command syntax!", NamedTextColor.RED)) }

    fun notPlayerMessage(p: CommandSender) { p.sendMessage(Component.text("Sender is not a player.")) }

    fun playerNotExistingMessage(p: CommandSender) { p.sendMessage(Component.text("This player does not exist!", NamedTextColor.RED)) }

    fun noPermissionMessage(p: CommandSender) { p.sendMessage(Component.text("You don't have permission to do that!", NamedTextColor.RED)) }

    fun cooldownMessage(p: Player, eta: Long) { p.sendMessage(Component.text("Please wait ${"%.1f".format(eta.toFloat())}s before using this!", NamedTextColor.YELLOW)) }

    fun isNumeric(input: String): Boolean { return input.toDoubleOrNull() != null }

    fun isChallengeEnabled(challenge: ChallengeManager, plugin: JavaPlugin): Boolean { return plugin.config.getBoolean("challenges.${challenge.name}.enabled") }

    fun setChallengeStatus(challenge: ChallengeManager, enabled: Boolean, plugin: JavaPlugin) { plugin.config.set("challenges.${challenge.name}.enabled", enabled) }
}
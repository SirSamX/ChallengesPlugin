package me.sirsam.challenges.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard

class Scoreboard {
    fun createScoreboard(): Scoreboard {
        val scoreboardManager = Bukkit.getScoreboardManager()
        val scoreboard = scoreboardManager.newScoreboard

        // Neues Objective registrieren
        val objective = scoreboard.registerNewObjective(
            "stats",
            Criteria.DUMMY,
            Component.text("§b§lServer Stats, ", NamedTextColor.DARK_GREEN)
        )
        // Anzeigeposition festlegen
        objective.displaySlot = DisplaySlot.SIDEBAR

        objective.getScore("maxitems: ${Material.values().size}")
        val collectedItems = "1"
        objective.getScore("Items: ${collectedItems}")

        return scoreboard
    }

    fun sendScoreboard(player: Player) {
        player.scoreboard = createScoreboard()
    }
}
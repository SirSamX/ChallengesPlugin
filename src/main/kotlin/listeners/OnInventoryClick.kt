package me.sirsam.challenges.listeners

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.guis.Challenges
import me.sirsam.challenges.guis.TimerGui
import me.sirsam.challenges.helpers.ChallengeStatus
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class OnInventoryClick : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val inv = event.clickedInventory
        if (inv?.holder !is TimerGui && inv?.holder !is Challenges) return
        val timer = ChallengeTimer.timer
        val player = event.whoClicked

        when (event.slot) {
            11 -> {
                timer.setStatus(ChallengeStatus.ACTIVE)
                player.openInventory(TimerGui().inventory)
            }

            13 -> {
                timer.setStatus(ChallengeStatus.PAUSED)
                player.openInventory(TimerGui().inventory)
            }

            15 -> {
                player.sendMessage(Component
                    .text("Are you sure you want to do this?\nClick here to confirm!", NamedTextColor.RED)
                    .hoverEvent(Component.text("/timer reset"))
                    .clickEvent(ClickEvent.runCommand("/timer reset"))
                )
                player.closeInventory()
            }

            26 -> {
                if (timer.isHidden()) timer.show() else timer.hide()
                player.openInventory(TimerGui().inventory)
            }

            0 -> {
                timer.reset()
                timer.setStatus(ChallengeStatus.ACTIVE)
                player.sendMessage(Component.text("The Challenge AllItems started!", NamedTextColor.GREEN))
                player.closeInventory()
            }

            4 -> {
                player.openInventory(Challenges().inventory)
            }

            27 -> {
                player.openInventory(TimerGui().inventory)
            }
        }

        event.isCancelled = true
    }
}
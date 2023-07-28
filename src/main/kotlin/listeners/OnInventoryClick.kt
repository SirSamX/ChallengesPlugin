package me.sirsam.challenges.listeners

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.Main
import me.sirsam.challenges.challanges.ChallengeManager
import me.sirsam.challenges.guis.Challenges
import me.sirsam.challenges.guis.TimerGui
import me.sirsam.challenges.helpers.ChallengeStatus
import me.sirsam.challenges.helpers.Utilities
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
        val player = event.whoClicked
        when (inv?.holder) {
            is TimerGui -> {
                val timer = ChallengeTimer.timer

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
                            .clickEvent(ClickEvent.runCommand("/challenges:timer reset"))
                        )
                        player.closeInventory()
                    }

                    26 -> {
                        if (timer.isHidden()) timer.show() else timer.hide()
                        player.openInventory(TimerGui().inventory)
                    }

                    18 -> {
                        player.openInventory(Challenges().inventory)
                    }
                }

                event.isCancelled = true
            }

            is Challenges -> {
                event.isCancelled = true
                val challenge: ChallengeManager
                try {
                    challenge = ChallengeManager.values()[event.slot]
                } catch (e: IndexOutOfBoundsException) {
                    return
                }
                if (Utilities().isChallengeEnabled(challenge, Main.getPlugin())) {
                    challenge.clazz.onDisable()
                    Utilities().setChallengeStatus(challenge, false, Main.getPlugin())
                    Utilities().broadcast(challenge.challengeName.append(Component.text(" disabled!", NamedTextColor.GREEN)))
                } else {
                    challenge.clazz.onEnable()
                    Utilities().setChallengeStatus(challenge, true, Main.getPlugin())
                    Utilities().broadcast(challenge.challengeName.append(Component.text(" enabled!", NamedTextColor.GREEN)))
                }
                player.openInventory(Challenges().inventory)
            }
        }
    }
}
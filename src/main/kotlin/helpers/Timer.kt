package me.sirsam.challenges.helpers

import me.sirsam.challenges.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class Timer {
    private val plugin = Main.getPlugin()
    private val utils = Utilities()
    private var status = ChallengeStatus.STOPPED
    private var hidden = false
    var time = 0

    fun setStatus(status: ChallengeStatus, broadcast: Boolean = true) {
        this.status = status

        if (!broadcast) return
        when (status) {
            ChallengeStatus.ACTIVE -> {
                utils.broadcast(Component.text("Timer Activated", NamedTextColor.GREEN))
            }

            ChallengeStatus.PAUSED -> {
                utils.broadcast(Component.text("Timer Paused", NamedTextColor.YELLOW))
            }

            ChallengeStatus.STOPPED -> {
                utils.broadcast(Component.text("Timer Stopped", NamedTextColor.RED))
            }
        }
    }

    fun getStatus(): ChallengeStatus {
        return status
    }

    fun isHidden(): Boolean {
        return hidden
    }

    fun show(broadcast: Boolean = true) {
        hidden = false
        object : BukkitRunnable() {
            override fun run() {
                when (status) {
                    ChallengeStatus.ACTIVE -> {
                        Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text("${time}s", NamedTextColor.DARK_PURPLE)) }
                        time++
                    }

                    ChallengeStatus.PAUSED -> {
                        Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text("Timer Paused", NamedTextColor.DARK_PURPLE).decorate(TextDecoration.ITALIC)) }
                    }

                    ChallengeStatus.STOPPED -> {
                        Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text("Timer Stopped", NamedTextColor.DARK_PURPLE).decorate(TextDecoration.ITALIC)) }
                    }
                }

                if (hidden) {
                    cancel()
                    Bukkit.getOnlinePlayers().forEach { p -> p.clearTitle() }
                }
            }
        }.runTaskTimer(plugin, 0, 20)

        if (!broadcast) return
        utils.broadcast(Component.text("Timer shown", NamedTextColor.GRAY))
    }

    fun hide(broadcast: Boolean = true) {
        hidden = true

        if (!broadcast) return
        utils.broadcast(Component.text("Timer hidden", NamedTextColor.GRAY))
    }
}
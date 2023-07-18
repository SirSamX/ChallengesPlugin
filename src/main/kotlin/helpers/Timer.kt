package me.sirsam.challenges.helpers

import me.sirsam.challenges.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import java.util.concurrent.TimeUnit

class Timer {
    private val plugin = Main.getPlugin()
    private val utils = Utilities()
    private var status = ChallengeStatus.STOPPED
    private var hidden = false
    var seconds: Long = 0

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
                        Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text(seconds.timerFormat(), NamedTextColor.DARK_PURPLE)) }
                        seconds++
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

    fun Long.timerFormat(): String {
        val sb = StringBuilder()

        with(TimeUnit.SECONDS) {
            val days = toDays(this@timerFormat)
            if (days > 0) {
                sb.append("%d:".format(days))
            }

            val hours = toHours(this@timerFormat) % 24
            if (hours > 0 || days > 0) {
                sb.append("%02d:".format(hours))
            }

            val minutes = toMinutes(this@timerFormat) % 60
            if (minutes > 0 || hours > 0 || days > 0) {
                sb.append("%02d:".format(minutes))
            }

            sb.append("%02d".format(this@timerFormat % 60))
        }

        return sb.toString()
    }
}
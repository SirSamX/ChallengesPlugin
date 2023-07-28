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
    private var seconds: Long = 0

    init {
        object : BukkitRunnable() {
            override fun run() {
                update()
                if (status != ChallengeStatus.ACTIVE) return
                seconds++
            }
        }.runTaskTimer(plugin, 0, 20)
    }

    fun update() {
        when (status) {
            ChallengeStatus.ACTIVE -> {
                if (hidden) return
                Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text(seconds.timerFormat(), NamedTextColor.DARK_PURPLE)) }
            }

            ChallengeStatus.PAUSED -> {
                if (hidden) return
                Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text("Timer Paused", NamedTextColor.DARK_PURPLE).decorate(TextDecoration.ITALIC)) }
            }

            ChallengeStatus.STOPPED -> {
                if (hidden) return
                Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text("Timer Stopped", NamedTextColor.DARK_PURPLE).decorate(TextDecoration.ITALIC)) }
            }
        }
    }

    fun setStatus(status: ChallengeStatus, broadcast: Boolean = true) {
        this.status = status
        update()

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
        update()

        if (!broadcast) return
        utils.broadcast(Component.text("Timer shown", NamedTextColor.GRAY))
    }

    fun hide(broadcast: Boolean = true) {
        hidden = true
        Bukkit.getOnlinePlayers().forEach { p -> p.sendActionBar(Component.text("")) }

        if (!broadcast) return
        utils.broadcast(Component.text("Timer hidden", NamedTextColor.GRAY))
    }

    fun get(): Long {
        return seconds
    }

    fun set(seconds: Long, broadcast: Boolean = true) {
        this.seconds = seconds

        if (broadcast) return
        utils.broadcast(utils.prefix.append(Component.text("Timer set to $seconds seconds!", NamedTextColor.GRAY)))
    }

    fun add(seconds: Long, broadcast: Boolean = true) {
        this.seconds += seconds

        if (broadcast) return
        utils.broadcast(utils.prefix.append(Component.text("Added $seconds seconds!", NamedTextColor.GRAY)))

    }

    fun remove(seconds: Long, broadcast: Boolean = true) {
        this.seconds -= seconds

        if (broadcast) return
        utils.broadcast(utils.prefix.append(Component.text("Removed $seconds seconds!", NamedTextColor.GRAY)))

    }


    fun reset(broadcast: Boolean = true) {
        setStatus(ChallengeStatus.STOPPED, broadcast)
        set(0)
    }

    private fun Long.timerFormat(): String {
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
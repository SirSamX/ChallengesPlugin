package me.sirsam.challenges

import me.sirsam.challenges.challanges.AllItems
import me.sirsam.challenges.challanges.ChallengeManager
import me.sirsam.challenges.commands.ChallengeCommand
import me.sirsam.challenges.commands.Kit
import me.sirsam.challenges.commands.TimerCommand
import me.sirsam.challenges.helpers.ChallengeStatus
import me.sirsam.challenges.helpers.Utilities
import me.sirsam.challenges.listeners.OnInventoryClick
import me.sirsam.challenges.listeners.OnJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val utils = Utilities()

    companion object {
        lateinit var instance: Main

        fun getPlugin(): Main {
            return instance
        }
    }

    override fun onEnable() {
        instance = this
        registerCommands()
        registerEvents()
        try {
            ChallengeTimer.timer.set(config.getLong("timer.seconds"), false)
            ChallengeTimer.timer.setStatus(ChallengeStatus.valueOf(config.getString("timer.status")!!), false)
        } catch (_: NullPointerException) {}
        enableChallenges()

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        config.set("timer.status", ChallengeTimer.timer.getStatus().name)
        config.set("timer.seconds", ChallengeTimer.timer.get())
        disableChallenges()
        saveConfig()

        logger.info("Plugin disabled!")
    }

    private fun enableChallenges() {
        for (challenge in ChallengeManager.values()) {
            if (utils.isChallengeEnabled(challenge, this)) challenge.clazz.onEnable()
        }
    }

    private fun disableChallenges() {
        for (challenge in ChallengeManager.values()) {
            if (utils.isChallengeEnabled(challenge, this)) challenge.clazz.onDisable()
        }
    }

    private fun registerCommands() {
        getCommand("challenge")?.setExecutor(ChallengeCommand())
        getCommand("challenge")?.tabCompleter = ChallengeCommand()
        getCommand("timer")?.setExecutor(TimerCommand())
        getCommand("timer")?.tabCompleter = TimerCommand()
        getCommand("kit")?.setExecutor(Kit())
        getCommand("kit")?.tabCompleter = Kit()
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(OnInventoryClick(), this)
        Bukkit.getPluginManager().registerEvents(OnJoin(), this)
        Bukkit.getPluginManager().registerEvents(AllItems(), this)
    }
}